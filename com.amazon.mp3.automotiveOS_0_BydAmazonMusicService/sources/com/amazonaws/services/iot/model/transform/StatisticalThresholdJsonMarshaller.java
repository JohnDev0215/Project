package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StatisticalThreshold;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class StatisticalThresholdJsonMarshaller {
    private static StatisticalThresholdJsonMarshaller instance;

    StatisticalThresholdJsonMarshaller() {
    }

    public void marshall(StatisticalThreshold statisticalThreshold, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (statisticalThreshold.getStatistic() != null) {
            String statistic = statisticalThreshold.getStatistic();
            awsJsonWriter.name("statistic");
            awsJsonWriter.value(statistic);
        }
        awsJsonWriter.endObject();
    }

    public static StatisticalThresholdJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StatisticalThresholdJsonMarshaller();
        }
        return instance;
    }
}
