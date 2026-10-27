package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.AuditMitigationActionExecutionMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionExecutionMetadataJsonUnmarshaller implements Unmarshaller<AuditMitigationActionExecutionMetadata, JsonUnmarshallerContext> {
    private static AuditMitigationActionExecutionMetadataJsonUnmarshaller instance;

    AuditMitigationActionExecutionMetadataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditMitigationActionExecutionMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditMitigationActionExecutionMetadata auditMitigationActionExecutionMetadata = new AuditMitigationActionExecutionMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskId")) {
                auditMitigationActionExecutionMetadata.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("findingId")) {
                auditMitigationActionExecutionMetadata.setFindingId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionName")) {
                auditMitigationActionExecutionMetadata.setActionName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionId")) {
                auditMitigationActionExecutionMetadata.setActionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                auditMitigationActionExecutionMetadata.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("startTime")) {
                auditMitigationActionExecutionMetadata.setStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("endTime")) {
                auditMitigationActionExecutionMetadata.setEndTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorCode")) {
                auditMitigationActionExecutionMetadata.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("message")) {
                auditMitigationActionExecutionMetadata.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditMitigationActionExecutionMetadata;
    }

    public static AuditMitigationActionExecutionMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionExecutionMetadataJsonUnmarshaller();
        }
        return instance;
    }
}
