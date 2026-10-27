package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StepFunctionsAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class StepFunctionsActionJsonMarshaller {
    private static StepFunctionsActionJsonMarshaller instance;

    StepFunctionsActionJsonMarshaller() {
    }

    public void marshall(StepFunctionsAction stepFunctionsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (stepFunctionsAction.getExecutionNamePrefix() != null) {
            String executionNamePrefix = stepFunctionsAction.getExecutionNamePrefix();
            awsJsonWriter.name("executionNamePrefix");
            awsJsonWriter.value(executionNamePrefix);
        }
        if (stepFunctionsAction.getStateMachineName() != null) {
            String stateMachineName = stepFunctionsAction.getStateMachineName();
            awsJsonWriter.name("stateMachineName");
            awsJsonWriter.value(stateMachineName);
        }
        if (stepFunctionsAction.getRoleArn() != null) {
            String roleArn = stepFunctionsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static StepFunctionsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StepFunctionsActionJsonMarshaller();
        }
        return instance;
    }
}
