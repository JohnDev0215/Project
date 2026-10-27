package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StartDetectMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class StartDetectMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<StartDetectMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static StartDetectMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public StartDetectMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        StartDetectMitigationActionsTaskResult startDetectMitigationActionsTaskResult = new StartDetectMitigationActionsTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskId")) {
                startDetectMitigationActionsTaskResult.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return startDetectMitigationActionsTaskResult;
    }

    public static StartDetectMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StartDetectMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
