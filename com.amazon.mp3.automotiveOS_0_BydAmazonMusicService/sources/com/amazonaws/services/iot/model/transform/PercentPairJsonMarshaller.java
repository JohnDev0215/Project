package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PercentPair;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class PercentPairJsonMarshaller {
    private static PercentPairJsonMarshaller instance;

    PercentPairJsonMarshaller() {
    }

    public void marshall(PercentPair percentPair, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (percentPair.getPercent() != null) {
            Double percent = percentPair.getPercent();
            awsJsonWriter.name("percent");
            awsJsonWriter.value(percent);
        }
        if (percentPair.getValue() != null) {
            Double value = percentPair.getValue();
            awsJsonWriter.name("value");
            awsJsonWriter.value(value);
        }
        awsJsonWriter.endObject();
    }

    public static PercentPairJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PercentPairJsonMarshaller();
        }
        return instance;
    }
}
