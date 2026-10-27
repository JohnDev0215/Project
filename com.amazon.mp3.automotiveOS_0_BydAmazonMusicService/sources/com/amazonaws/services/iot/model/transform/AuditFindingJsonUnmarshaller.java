package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditFinding;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuditFindingJsonUnmarshaller implements Unmarshaller<AuditFinding, JsonUnmarshallerContext> {
    private static AuditFindingJsonUnmarshaller instance;

    AuditFindingJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditFinding unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditFinding auditFinding = new AuditFinding();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("findingId")) {
                auditFinding.setFindingId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskId")) {
                auditFinding.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("checkName")) {
                auditFinding.setCheckName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStartTime")) {
                auditFinding.setTaskStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("findingTime")) {
                auditFinding.setFindingTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("severity")) {
                auditFinding.setSeverity(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nonCompliantResource")) {
                auditFinding.setNonCompliantResource(NonCompliantResourceJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("relatedResources")) {
                auditFinding.setRelatedResources(new ListUnmarshaller(RelatedResourceJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("reasonForNonCompliance")) {
                auditFinding.setReasonForNonCompliance(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("reasonForNonComplianceCode")) {
                auditFinding.setReasonForNonComplianceCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isSuppressed")) {
                auditFinding.setIsSuppressed(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditFinding;
    }

    public static AuditFindingJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditFindingJsonUnmarshaller();
        }
        return instance;
    }
}
