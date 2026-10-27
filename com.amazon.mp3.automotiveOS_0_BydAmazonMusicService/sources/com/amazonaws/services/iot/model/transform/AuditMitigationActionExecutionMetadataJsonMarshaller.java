package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.AuditMitigationActionExecutionMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionExecutionMetadataJsonMarshaller {
    private static AuditMitigationActionExecutionMetadataJsonMarshaller instance;

    AuditMitigationActionExecutionMetadataJsonMarshaller() {
    }

    public void marshall(AuditMitigationActionExecutionMetadata auditMitigationActionExecutionMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditMitigationActionExecutionMetadata.getTaskId() != null) {
            String taskId = auditMitigationActionExecutionMetadata.getTaskId();
            awsJsonWriter.name("taskId");
            awsJsonWriter.value(taskId);
        }
        if (auditMitigationActionExecutionMetadata.getFindingId() != null) {
            String findingId = auditMitigationActionExecutionMetadata.getFindingId();
            awsJsonWriter.name("findingId");
            awsJsonWriter.value(findingId);
        }
        if (auditMitigationActionExecutionMetadata.getActionName() != null) {
            String actionName = auditMitigationActionExecutionMetadata.getActionName();
            awsJsonWriter.name("actionName");
            awsJsonWriter.value(actionName);
        }
        if (auditMitigationActionExecutionMetadata.getActionId() != null) {
            String actionId = auditMitigationActionExecutionMetadata.getActionId();
            awsJsonWriter.name("actionId");
            awsJsonWriter.value(actionId);
        }
        if (auditMitigationActionExecutionMetadata.getStatus() != null) {
            String status = auditMitigationActionExecutionMetadata.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (auditMitigationActionExecutionMetadata.getStartTime() != null) {
            Date startTime = auditMitigationActionExecutionMetadata.getStartTime();
            awsJsonWriter.name("startTime");
            awsJsonWriter.value(startTime);
        }
        if (auditMitigationActionExecutionMetadata.getEndTime() != null) {
            Date endTime = auditMitigationActionExecutionMetadata.getEndTime();
            awsJsonWriter.name("endTime");
            awsJsonWriter.value(endTime);
        }
        if (auditMitigationActionExecutionMetadata.getErrorCode() != null) {
            String errorCode = auditMitigationActionExecutionMetadata.getErrorCode();
            awsJsonWriter.name("errorCode");
            awsJsonWriter.value(errorCode);
        }
        if (auditMitigationActionExecutionMetadata.getMessage() != null) {
            String message = auditMitigationActionExecutionMetadata.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        awsJsonWriter.endObject();
    }

    public static AuditMitigationActionExecutionMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionExecutionMetadataJsonMarshaller();
        }
        return instance;
    }
}
