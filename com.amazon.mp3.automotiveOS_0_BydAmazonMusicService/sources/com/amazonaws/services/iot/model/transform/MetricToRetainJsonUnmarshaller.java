package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricToRetain;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MetricToRetainJsonUnmarshaller implements Unmarshaller<MetricToRetain, JsonUnmarshallerContext> {
    private static MetricToRetainJsonUnmarshaller instance;

    MetricToRetainJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricToRetain unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricToRetain metricToRetain = new MetricToRetain();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metric")) {
                metricToRetain.setMetric(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricDimension")) {
                metricToRetain.setMetricDimension(MetricDimensionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricToRetain;
    }

    public static MetricToRetainJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricToRetainJsonUnmarshaller();
        }
        return instance;
    }
}
