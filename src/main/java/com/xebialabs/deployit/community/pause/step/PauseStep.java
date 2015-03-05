/**
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS
 * FOR A PARTICULAR PURPOSE. THIS CODE AND INFORMATION ARE NOT SUPPORTED BY XEBIALABS.
 */
package com.xebialabs.deployit.community.pause.step;

import com.xebialabs.deployit.plugin.api.flow.ExecutionContext;
import com.xebialabs.deployit.plugin.api.flow.Step;
import com.xebialabs.deployit.plugin.api.flow.StepExitCode;

public class PauseStep implements Step {

    private final int order;
    private boolean executedOnce = false;

    public PauseStep(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String getDescription() {
        return "Pause the task";
    }

    @Override
    public StepExitCode execute(final ExecutionContext executionContext) throws Exception {
        if (!executedOnce) {
            executedOnce = true;
            return StepExitCode.PAUSE;
        }
        return StepExitCode.SUCCESS;

    }

}
