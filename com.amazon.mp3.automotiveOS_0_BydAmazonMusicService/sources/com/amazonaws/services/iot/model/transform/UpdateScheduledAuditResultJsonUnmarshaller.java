package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateScheduledAuditResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateScheduledAuditResultJsonUnmarshaller implements Unmarshaller<UpdateScheduledAuditResult, JsonUnmarshallerContext> {
    private static UpdateScheduledAuditResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateScheduledAuditResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateScheduledAuditResult updateScheduledAuditResult = new UpdateScheduledAuditResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("scheduledAuditArn")) {
                updateScheduledAuditResult.setScheduledAuditArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateScheduledAuditResult;
    }

    public static UpdateScheduledAuditResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateScheduledAuditResultJsonUnmarshaller();
        }
        return instance;
    }
}
