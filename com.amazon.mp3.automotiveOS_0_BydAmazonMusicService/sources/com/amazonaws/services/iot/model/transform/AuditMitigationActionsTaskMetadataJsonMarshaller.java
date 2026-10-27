package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditMitigationActionsTaskMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionsTaskMetadataJsonMarshaller {
    private static AuditMitigationActionsTaskMetadataJsonMarshaller instance;

    AuditMitigationActionsTaskMetadataJsonMarshaller() {
    }

    public void marshall(AuditMitigationActionsTaskMetadata auditMitigationActionsTaskMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditMitigationActionsTaskMetadata.getTaskId() != null) {
            String taskId = auditMitigationActionsTaskMetadata.getTaskId();
            awsJsonWriter.name("taskId");
            awsJsonWriter.value(taskId);
        }
        if (auditMitigationActionsTaskMetadata.getStartTime() != null) {
            Date startTime = auditMitigationActionsTaskMetadata.getStartTime();
            awsJsonWriter.name("startTime");
            awsJsonWriter.value(startTime);
        }
        if (auditMitigationActionsTaskMetadata.getTaskStatus() != null) {
            String taskStatus = auditMitigationActionsTaskMetadata.getTaskStatus();
            awsJsonWriter.name("taskStatus");
            awsJsonWriter.value(taskStatus);
        }
        awsJsonWriter.endObject();
    }

    public static AuditMitigationActionsTaskMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionsTaskMetadataJsonMarshaller();
        }
        return instance;
    }
}
