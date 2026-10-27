package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateScheduledAuditResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateScheduledAuditResultJsonUnmarshaller implements Unmarshaller<CreateScheduledAuditResult, JsonUnmarshallerContext> {
    private static CreateScheduledAuditResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateScheduledAuditResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateScheduledAuditResult createScheduledAuditResult = new CreateScheduledAuditResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("scheduledAuditArn")) {
                createScheduledAuditResult.setScheduledAuditArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createScheduledAuditResult;
    }

    public static CreateScheduledAuditResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateScheduledAuditResultJsonUnmarshaller();
        }
        return instance;
    }
}
