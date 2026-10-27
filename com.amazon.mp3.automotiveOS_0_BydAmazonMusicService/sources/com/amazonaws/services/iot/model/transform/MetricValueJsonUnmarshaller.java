package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MetricValueJsonUnmarshaller implements Unmarshaller<MetricValue, JsonUnmarshallerContext> {
    private static MetricValueJsonUnmarshaller instance;

    MetricValueJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricValue unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricValue metricValue = new MetricValue();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("count")) {
                metricValue.setCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("cidrs")) {
                metricValue.setCidrs(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ports")) {
                metricValue.setPorts(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("number")) {
                metricValue.setNumber(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("numbers")) {
                metricValue.setNumbers(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("strings")) {
                metricValue.setStrings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricValue;
    }

    public static MetricValueJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricValueJsonUnmarshaller();
        }
        return instance;
    }
}
