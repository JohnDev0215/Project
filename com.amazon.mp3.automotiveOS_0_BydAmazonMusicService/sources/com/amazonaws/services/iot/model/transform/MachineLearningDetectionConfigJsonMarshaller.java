package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MachineLearningDetectionConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class MachineLearningDetectionConfigJsonMarshaller {
    private static MachineLearningDetectionConfigJsonMarshaller instance;

    MachineLearningDetectionConfigJsonMarshaller() {
    }

    public void marshall(MachineLearningDetectionConfig machineLearningDetectionConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (machineLearningDetectionConfig.getConfidenceLevel() != null) {
            String confidenceLevel = machineLearningDetectionConfig.getConfidenceLevel();
            awsJsonWriter.name("confidenceLevel");
            awsJsonWriter.value(confidenceLevel);
        }
        awsJsonWriter.endObject();
    }

    public static MachineLearningDetectionConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MachineLearningDetectionConfigJsonMarshaller();
        }
        return instance;
    }
}
