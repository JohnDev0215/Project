package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskTarget;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskTargetJsonMarshaller {
    private static DetectMitigationActionsTaskTargetJsonMarshaller instance;

    DetectMitigationActionsTaskTargetJsonMarshaller() {
    }

    public void marshall(DetectMitigationActionsTaskTarget detectMitigationActionsTaskTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (detectMitigationActionsTaskTarget.getViolationIds() != null) {
            List<String> violationIds = detectMitigationActionsTaskTarget.getViolationIds();
            awsJsonWriter.name("violationIds");
            awsJsonWriter.beginArray();
            for (String str : violationIds) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (detectMitigationActionsTaskTarget.getSecurityProfileName() != null) {
            String securityProfileName = detectMitigationActionsTaskTarget.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (detectMitigationActionsTaskTarget.getBehaviorName() != null) {
            String behaviorName = detectMitigationActionsTaskTarget.getBehaviorName();
            awsJsonWriter.name("behaviorName");
            awsJsonWriter.value(behaviorName);
        }
        awsJsonWriter.endObject();
    }

    public static DetectMitigationActionsTaskTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskTargetJsonMarshaller();
        }
        return instance;
    }
}
