package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StatisticalThreshold;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class StatisticalThresholdJsonUnmarshaller implements Unmarshaller<StatisticalThreshold, JsonUnmarshallerContext> {
    private static StatisticalThresholdJsonUnmarshaller instance;

    StatisticalThresholdJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StatisticalThreshold unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        StatisticalThreshold statisticalThreshold = new StatisticalThreshold();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("statistic")) {
                statisticalThreshold.setStatistic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return statisticalThreshold;
    }

    public static StatisticalThresholdJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StatisticalThresholdJsonUnmarshaller();
        }
        return instance;
    }
}
