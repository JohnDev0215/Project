package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditMitigationActionsTaskMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionsTaskMetadataJsonUnmarshaller implements Unmarshaller<AuditMitigationActionsTaskMetadata, JsonUnmarshallerContext> {
    private static AuditMitigationActionsTaskMetadataJsonUnmarshaller instance;

    AuditMitigationActionsTaskMetadataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditMitigationActionsTaskMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditMitigationActionsTaskMetadata auditMitigationActionsTaskMetadata = new AuditMitigationActionsTaskMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskId")) {
                auditMitigationActionsTaskMetadata.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("startTime")) {
                auditMitigationActionsTaskMetadata.setStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStatus")) {
                auditMitigationActionsTaskMetadata.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditMitigationActionsTaskMetadata;
    }

    public static AuditMitigationActionsTaskMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionsTaskMetadataJsonUnmarshaller();
        }
        return instance;
    }
}
