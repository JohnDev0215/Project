package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskTargetJsonUnmarshaller implements Unmarshaller<DetectMitigationActionsTaskTarget, JsonUnmarshallerContext> {
    private static DetectMitigationActionsTaskTargetJsonUnmarshaller instance;

    DetectMitigationActionsTaskTargetJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetectMitigationActionsTaskTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DetectMitigationActionsTaskTarget detectMitigationActionsTaskTarget = new DetectMitigationActionsTaskTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("violationIds")) {
                detectMitigationActionsTaskTarget.setViolationIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityProfileName")) {
                detectMitigationActionsTaskTarget.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("behaviorName")) {
                detectMitigationActionsTaskTarget.setBehaviorName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return detectMitigationActionsTaskTarget;
    }

    public static DetectMitigationActionsTaskTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskTargetJsonUnmarshaller();
        }
        return instance;
    }
}
