package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ActiveViolation;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ActiveViolationJsonUnmarshaller implements Unmarshaller<ActiveViolation, JsonUnmarshallerContext> {
    private static ActiveViolationJsonUnmarshaller instance;

    ActiveViolationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ActiveViolation unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ActiveViolation activeViolation = new ActiveViolation();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("violationId")) {
                activeViolation.setViolationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingName")) {
                activeViolation.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityProfileName")) {
                activeViolation.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("behavior")) {
                activeViolation.setBehavior(BehaviorJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastViolationValue")) {
                activeViolation.setLastViolationValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationEventAdditionalInfo")) {
                activeViolation.setViolationEventAdditionalInfo(ViolationEventAdditionalInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("verificationState")) {
                activeViolation.setVerificationState(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("verificationStateDescription")) {
                activeViolation.setVerificationStateDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastViolationTime")) {
                activeViolation.setLastViolationTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationStartTime")) {
                activeViolation.setViolationStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return activeViolation;
    }

    public static ActiveViolationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ActiveViolationJsonUnmarshaller();
        }
        return instance;
    }
}
