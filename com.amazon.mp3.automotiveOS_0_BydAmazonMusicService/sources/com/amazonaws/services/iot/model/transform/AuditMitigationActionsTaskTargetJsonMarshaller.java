package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditMitigationActionsTaskTarget;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class AuditMitigationActionsTaskTargetJsonMarshaller {
    private static AuditMitigationActionsTaskTargetJsonMarshaller instance;

    AuditMitigationActionsTaskTargetJsonMarshaller() {
    }

    public void marshall(AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditMitigationActionsTaskTarget.getAuditTaskId() != null) {
            String auditTaskId = auditMitigationActionsTaskTarget.getAuditTaskId();
            awsJsonWriter.name("auditTaskId");
            awsJsonWriter.value(auditTaskId);
        }
        if (auditMitigationActionsTaskTarget.getFindingIds() != null) {
            List<String> findingIds = auditMitigationActionsTaskTarget.getFindingIds();
            awsJsonWriter.name("findingIds");
            awsJsonWriter.beginArray();
            for (String str : findingIds) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (auditMitigationActionsTaskTarget.getAuditCheckToReasonCodeFilter() != null) {
            Map<String, List<String>> auditCheckToReasonCodeFilter = auditMitigationActionsTaskTarget.getAuditCheckToReasonCodeFilter();
            awsJsonWriter.name("auditCheckToReasonCodeFilter");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, List<String>> entry : auditCheckToReasonCodeFilter.entrySet()) {
                List<String> value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.beginArray();
                    for (String str2 : value) {
                        if (str2 != null) {
                            awsJsonWriter.value(str2);
                        }
                    }
                    awsJsonWriter.endArray();
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }

    public static AuditMitigationActionsTaskTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditMitigationActionsTaskTargetJsonMarshaller();
        }
        return instance;
    }
}
