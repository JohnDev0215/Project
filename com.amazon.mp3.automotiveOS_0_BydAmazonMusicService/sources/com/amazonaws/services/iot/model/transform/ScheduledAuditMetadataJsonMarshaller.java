package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ScheduledAuditMetadata;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ScheduledAuditMetadataJsonMarshaller {
    private static ScheduledAuditMetadataJsonMarshaller instance;

    ScheduledAuditMetadataJsonMarshaller() {
    }

    public void marshall(ScheduledAuditMetadata scheduledAuditMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (scheduledAuditMetadata.getScheduledAuditName() != null) {
            String scheduledAuditName = scheduledAuditMetadata.getScheduledAuditName();
            awsJsonWriter.name("scheduledAuditName");
            awsJsonWriter.value(scheduledAuditName);
        }
        if (scheduledAuditMetadata.getScheduledAuditArn() != null) {
            String scheduledAuditArn = scheduledAuditMetadata.getScheduledAuditArn();
            awsJsonWriter.name("scheduledAuditArn");
            awsJsonWriter.value(scheduledAuditArn);
        }
        if (scheduledAuditMetadata.getFrequency() != null) {
            String frequency = scheduledAuditMetadata.getFrequency();
            awsJsonWriter.name("frequency");
            awsJsonWriter.value(frequency);
        }
        if (scheduledAuditMetadata.getDayOfMonth() != null) {
            String dayOfMonth = scheduledAuditMetadata.getDayOfMonth();
            awsJsonWriter.name("dayOfMonth");
            awsJsonWriter.value(dayOfMonth);
        }
        if (scheduledAuditMetadata.getDayOfWeek() != null) {
            String dayOfWeek = scheduledAuditMetadata.getDayOfWeek();
            awsJsonWriter.name("dayOfWeek");
            awsJsonWriter.value(dayOfWeek);
        }
        awsJsonWriter.endObject();
    }

    public static ScheduledAuditMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ScheduledAuditMetadataJsonMarshaller();
        }
        return instance;
    }
}
