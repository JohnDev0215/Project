package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricDimension;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MetricDimensionJsonUnmarshaller implements Unmarshaller<MetricDimension, JsonUnmarshallerContext> {
    private static MetricDimensionJsonUnmarshaller instance;

    MetricDimensionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricDimension unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricDimension metricDimension = new MetricDimension();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("dimensionName")) {
                metricDimension.setDimensionName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("operator")) {
                metricDimension.setOperator(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricDimension;
    }

    public static MetricDimensionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricDimensionJsonUnmarshaller();
        }
        return instance;
    }
}
