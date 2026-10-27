package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StepFunctionsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class StepFunctionsActionJsonUnmarshaller implements Unmarshaller<StepFunctionsAction, JsonUnmarshallerContext> {
    private static StepFunctionsActionJsonUnmarshaller instance;

    StepFunctionsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StepFunctionsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        StepFunctionsAction stepFunctionsAction = new StepFunctionsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("executionNamePrefix")) {
                stepFunctionsAction.setExecutionNamePrefix(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stateMachineName")) {
                stepFunctionsAction.setStateMachineName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                stepFunctionsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return stepFunctionsAction;
    }

    public static StepFunctionsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StepFunctionsActionJsonUnmarshaller();
        }
        return instance;
    }
}
