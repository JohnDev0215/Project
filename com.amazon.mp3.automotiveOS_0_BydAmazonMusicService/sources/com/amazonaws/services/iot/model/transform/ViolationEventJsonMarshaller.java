package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.services.iot.model.ViolationEvent;
import com.amazonaws.services.iot.model.ViolationEventAdditionalInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventJsonMarshaller {
    private static ViolationEventJsonMarshaller instance;

    ViolationEventJsonMarshaller() {
    }

    public void marshall(ViolationEvent violationEvent, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (violationEvent.getViolationId() != null) {
            String violationId = violationEvent.getViolationId();
            awsJsonWriter.name("violationId");
            awsJsonWriter.value(violationId);
        }
        if (violationEvent.getThingName() != null) {
            String thingName = violationEvent.getThingName();
            awsJsonWriter.name("thingName");
            awsJsonWriter.value(thingName);
        }
        if (violationEvent.getSecurityProfileName() != null) {
            String securityProfileName = violationEvent.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (violationEvent.getBehavior() != null) {
            Behavior behavior = violationEvent.getBehavior();
            awsJsonWriter.name("behavior");
            BehaviorJsonMarshaller.getInstance().marshall(behavior, awsJsonWriter);
        }
        if (violationEvent.getMetricValue() != null) {
            MetricValue metricValue = violationEvent.getMetricValue();
            awsJsonWriter.name("metricValue");
            MetricValueJsonMarshaller.getInstance().marshall(metricValue, awsJsonWriter);
        }
        if (violationEvent.getViolationEventAdditionalInfo() != null) {
            ViolationEventAdditionalInfo violationEventAdditionalInfo = violationEvent.getViolationEventAdditionalInfo();
            awsJsonWriter.name("violationEventAdditionalInfo");
            ViolationEventAdditionalInfoJsonMarshaller.getInstance().marshall(violationEventAdditionalInfo, awsJsonWriter);
        }
        if (violationEvent.getViolationEventType() != null) {
            String violationEventType = violationEvent.getViolationEventType();
            awsJsonWriter.name("violationEventType");
            awsJsonWriter.value(violationEventType);
        }
        if (violationEvent.getVerificationState() != null) {
            String verificationState = violationEvent.getVerificationState();
            awsJsonWriter.name("verificationState");
            awsJsonWriter.value(verificationState);
        }
        if (violationEvent.getVerificationStateDescription() != null) {
            String verificationStateDescription = violationEvent.getVerificationStateDescription();
            awsJsonWriter.name("verificationStateDescription");
            awsJsonWriter.value(verificationStateDescription);
        }
        if (violationEvent.getViolationEventTime() != null) {
            Date violationEventTime = violationEvent.getViolationEventTime();
            awsJsonWriter.name("violationEventTime");
            awsJsonWriter.value(violationEventTime);
        }
        awsJsonWriter.endObject();
    }

    public static ViolationEventJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventJsonMarshaller();
        }
        return instance;
    }
}
