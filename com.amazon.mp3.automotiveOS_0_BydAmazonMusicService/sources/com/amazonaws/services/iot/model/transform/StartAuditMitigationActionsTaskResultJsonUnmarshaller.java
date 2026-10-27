package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StartAuditMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class StartAuditMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<StartAuditMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static StartAuditMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public StartAuditMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        StartAuditMitigationActionsTaskResult startAuditMitigationActionsTaskResult = new StartAuditMitigationActionsTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskId")) {
                startAuditMitigationActionsTaskResult.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return startAuditMitigationActionsTaskResult;
    }

    public static StartAuditMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StartAuditMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
