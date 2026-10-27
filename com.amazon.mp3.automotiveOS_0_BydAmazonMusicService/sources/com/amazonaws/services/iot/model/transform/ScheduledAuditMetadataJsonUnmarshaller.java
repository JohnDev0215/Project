package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ScheduledAuditMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ScheduledAuditMetadataJsonUnmarshaller implements Unmarshaller<ScheduledAuditMetadata, JsonUnmarshallerContext> {
    private static ScheduledAuditMetadataJsonUnmarshaller instance;

    ScheduledAuditMetadataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ScheduledAuditMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ScheduledAuditMetadata scheduledAuditMetadata = new ScheduledAuditMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("scheduledAuditName")) {
                scheduledAuditMetadata.setScheduledAuditName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("scheduledAuditArn")) {
                scheduledAuditMetadata.setScheduledAuditArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("frequency")) {
                scheduledAuditMetadata.setFrequency(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dayOfMonth")) {
                scheduledAuditMetadata.setDayOfMonth(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dayOfWeek")) {
                scheduledAuditMetadata.setDayOfWeek(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return scheduledAuditMetadata;
    }

    public static ScheduledAuditMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ScheduledAuditMetadataJsonUnmarshaller();
        }
        return instance;
    }
}
