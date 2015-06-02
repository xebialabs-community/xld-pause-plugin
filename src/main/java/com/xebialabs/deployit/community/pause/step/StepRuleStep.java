/**
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS
 * FOR A PARTICULAR PURPOSE. THIS CODE AND INFORMATION ARE NOT SUPPORTED BY XEBIALABS.
 */
package com.xebialabs.deployit.community.pause.step;

import com.xebialabs.deployit.plugin.api.flow.ExecutionContext;
import com.xebialabs.deployit.plugin.api.flow.Step;
import com.xebialabs.deployit.plugin.api.flow.StepExitCode;
import com.xebialabs.deployit.plugin.api.rules.RulePostConstruct;
import com.xebialabs.deployit.plugin.api.rules.StepMetadata;
import com.xebialabs.deployit.plugin.api.rules.StepParameter;
import com.xebialabs.deployit.plugin.api.rules.StepPostConstructContext;

@StepMetadata(name = "pause")
public class StepRuleStep implements Step {

    @StepParameter(name = "order", description = "The execution order of this step", calculated = true)
    private int order = 0;

    @StepParameter(name = "description", description = "Description of this step", calculated = true)
    private String description = "";

    private boolean executedOnce = false;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public StepExitCode execute(final ExecutionContext ctx) throws Exception {
        if (!executedOnce) {
            executedOnce = true;
            return StepExitCode.PAUSE;
        }
        return StepExitCode.SUCCESS;
    }

    @RulePostConstruct
    private void postContstruct(StepPostConstructContext ctx) {
        if (order == 0) {
            order = 50;
        }
        if (description.isEmpty()) {
            description = "Pause the task";
        }

    }

}
