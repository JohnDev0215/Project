package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditMitigationActionsTaskTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionsTaskTargetJsonUnmarshaller implements Unmarshaller<AuditMitigationActionsTaskTarget, JsonUnmarshallerContext> {
    private static AuditMitigationActionsTaskTargetJsonUnmarshaller instance;

    AuditMitigationActionsTaskTargetJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditMitigationActionsTaskTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget = new AuditMitigationActionsTaskTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("auditTaskId")) {
                auditMitigationActionsTaskTarget.setAuditTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("findingIds")) {
                auditMitigationActionsTaskTarget.setFindingIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("auditCheckToReasonCodeFilter")) {
                auditMitigationActionsTaskTarget.setAuditCheckToReasonCodeFilter(new MapUnmarshaller(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance())).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditMitigationActionsTaskTarget;
    }

    public static AuditMitigationActionsTaskTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionsTaskTargetJsonUnmarshaller();
        }
        return instance;
    }
}
