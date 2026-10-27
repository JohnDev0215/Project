package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ActiveViolation;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.services.iot.model.ViolationEventAdditionalInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class ActiveViolationJsonMarshaller {
    private static ActiveViolationJsonMarshaller instance;

    ActiveViolationJsonMarshaller() {
    }

    public void marshall(ActiveViolation activeViolation, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (activeViolation.getViolationId() != null) {
            String violationId = activeViolation.getViolationId();
            awsJsonWriter.name("violationId");
            awsJsonWriter.value(violationId);
        }
        if (activeViolation.getThingName() != null) {
            String thingName = activeViolation.getThingName();
            awsJsonWriter.name("thingName");
            awsJsonWriter.value(thingName);
        }
        if (activeViolation.getSecurityProfileName() != null) {
            String securityProfileName = activeViolation.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (activeViolation.getBehavior() != null) {
            Behavior behavior = activeViolation.getBehavior();
            awsJsonWriter.name("behavior");
            BehaviorJsonMarshaller.getInstance().marshall(behavior, awsJsonWriter);
        }
        if (activeViolation.getLastViolationValue() != null) {
            MetricValue lastViolationValue = activeViolation.getLastViolationValue();
            awsJsonWriter.name("lastViolationValue");
            MetricValueJsonMarshaller.getInstance().marshall(lastViolationValue, awsJsonWriter);
        }
        if (activeViolation.getViolationEventAdditionalInfo() != null) {
            ViolationEventAdditionalInfo violationEventAdditionalInfo = activeViolation.getViolationEventAdditionalInfo();
            awsJsonWriter.name("violationEventAdditionalInfo");
            ViolationEventAdditionalInfoJsonMarshaller.getInstance().marshall(violationEventAdditionalInfo, awsJsonWriter);
        }
        if (activeViolation.getVerificationState() != null) {
            String verificationState = activeViolation.getVerificationState();
            awsJsonWriter.name("verificationState");
            awsJsonWriter.value(verificationState);
        }
        if (activeViolation.getVerificationStateDescription() != null) {
            String verificationStateDescription = activeViolation.getVerificationStateDescription();
            awsJsonWriter.name("verificationStateDescription");
            awsJsonWriter.value(verificationStateDescription);
        }
        if (activeViolation.getLastViolationTime() != null) {
            Date lastViolationTime = activeViolation.getLastViolationTime();
            awsJsonWriter.name("lastViolationTime");
            awsJsonWriter.value(lastViolationTime);
        }
        if (activeViolation.getViolationStartTime() != null) {
            Date violationStartTime = activeViolation.getViolationStartTime();
            awsJsonWriter.name("violationStartTime");
            awsJsonWriter.value(violationStartTime);
        }
        awsJsonWriter.endObject();
    }

    public static ActiveViolationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ActiveViolationJsonMarshaller();
        }
        return instance;
    }
}
