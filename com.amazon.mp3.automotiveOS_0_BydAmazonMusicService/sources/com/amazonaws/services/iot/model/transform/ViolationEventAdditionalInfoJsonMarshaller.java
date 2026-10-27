package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ViolationEventAdditionalInfo;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventAdditionalInfoJsonMarshaller {
    private static ViolationEventAdditionalInfoJsonMarshaller instance;

    ViolationEventAdditionalInfoJsonMarshaller() {
    }

    public void marshall(ViolationEventAdditionalInfo violationEventAdditionalInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (violationEventAdditionalInfo.getConfidenceLevel() != null) {
            String confidenceLevel = violationEventAdditionalInfo.getConfidenceLevel();
            awsJsonWriter.name("confidenceLevel");
            awsJsonWriter.value(confidenceLevel);
        }
        awsJsonWriter.endObject();
    }

    public static ViolationEventAdditionalInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventAdditionalInfoJsonMarshaller();
        }
        return instance;
    }
}
