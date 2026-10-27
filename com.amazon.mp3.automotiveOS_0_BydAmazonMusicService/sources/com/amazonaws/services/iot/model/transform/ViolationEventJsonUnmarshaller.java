package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ViolationEvent;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventJsonUnmarshaller implements Unmarshaller<ViolationEvent, JsonUnmarshallerContext> {
    private static ViolationEventJsonUnmarshaller instance;

    ViolationEventJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ViolationEvent unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ViolationEvent violationEvent = new ViolationEvent();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("violationId")) {
                violationEvent.setViolationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingName")) {
                violationEvent.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityProfileName")) {
                violationEvent.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("behavior")) {
                violationEvent.setBehavior(BehaviorJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricValue")) {
                violationEvent.setMetricValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationEventAdditionalInfo")) {
                violationEvent.setViolationEventAdditionalInfo(ViolationEventAdditionalInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationEventType")) {
                violationEvent.setViolationEventType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("verificationState")) {
                violationEvent.setVerificationState(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("verificationStateDescription")) {
                violationEvent.setVerificationStateDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationEventTime")) {
                violationEvent.setViolationEventTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return violationEvent;
    }

    public static ViolationEventJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventJsonUnmarshaller();
        }
        return instance;
    }
}
