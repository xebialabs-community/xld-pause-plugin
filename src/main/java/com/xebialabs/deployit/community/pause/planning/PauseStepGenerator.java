/**
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS
 * FOR A PARTICULAR PURPOSE. THIS CODE AND INFORMATION ARE NOT SUPPORTED BY XEBIALABS.
 */
package com.xebialabs.deployit.community.pause.planning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Predicate;

import com.xebialabs.deployit.community.pause.step.PauseStep;
import com.xebialabs.deployit.plugin.api.deployment.planning.Contributor;
import com.xebialabs.deployit.plugin.api.deployment.planning.DeploymentPlanningContext;
import com.xebialabs.deployit.plugin.api.deployment.specification.Delta;
import com.xebialabs.deployit.plugin.api.deployment.specification.Deltas;
import com.xebialabs.deployit.plugin.api.deployment.specification.Operation;
import com.xebialabs.deployit.plugin.api.udm.Environment;

import static com.google.common.collect.Iterables.all;

public class PauseStepGenerator {

	static final String PAUSABLE_PROPERTY = "pausable";
	static final String PAUSE_ORDER_PROPERTY = "pauseOrder";
	static final String PAUSE_ON_NOOP = "pauseOnNoop";
	static final String PAUSE_ON_UNDEPLOY = "pauseOnUndeploy";

	static final Predicate<Delta> NOOP_OPERATION = new Predicate<Delta>() {
		@Override
		public boolean apply(Delta input) {
			return input.getOperation() == Operation.NOOP;
		}
	};

	static final Predicate<Delta> DESTROY_OPERATION = new Predicate<Delta>() {
		@Override
		public boolean apply(Delta input) {
			return input.getOperation() == Operation.DESTROY;
		}
	};

	@Contributor
	public static void contribute(Deltas deltas, DeploymentPlanningContext context) {
		final Environment environment = context.getDeployedApplication().getEnvironment();
		if (all(deltas.getDeltas(), NOOP_OPERATION) && disabled(PAUSE_ON_NOOP, environment))
			return;

		if (all(deltas.getDeltas(), DESTROY_OPERATION) && disabled(PAUSE_ON_UNDEPLOY, environment))
			return;

		addPauseStep(environment, context);
	}

	private static void addPauseStep(final Environment environment, DeploymentPlanningContext context) {
		if (enabled(PAUSABLE_PROPERTY, environment)) {
			final int order = environment.<Integer>getProperty(PAUSE_ORDER_PROPERTY);
			logger.debug("new PauseStep order {}", order);
			context.addStep(new PauseStep(order));
		}
	}

	private static boolean disabled(String propertyName, final Environment environment) {
		return !enabled(propertyName, environment);
	}

	private static boolean enabled(String propertyName, final Environment environment) {
		return environment.hasProperty(propertyName) && environment.<Boolean>getProperty(propertyName);
	}

	protected static final Logger logger = LoggerFactory.getLogger(PauseStepGenerator.class);

}
