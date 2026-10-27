package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MachineLearningDetectionConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MachineLearningDetectionConfigJsonUnmarshaller implements Unmarshaller<MachineLearningDetectionConfig, JsonUnmarshallerContext> {
    private static MachineLearningDetectionConfigJsonUnmarshaller instance;

    MachineLearningDetectionConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MachineLearningDetectionConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MachineLearningDetectionConfig machineLearningDetectionConfig = new MachineLearningDetectionConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("confidenceLevel")) {
                machineLearningDetectionConfig.setConfidenceLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return machineLearningDetectionConfig;
    }

    public static MachineLearningDetectionConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MachineLearningDetectionConfigJsonUnmarshaller();
        }
        return instance;
    }
}
