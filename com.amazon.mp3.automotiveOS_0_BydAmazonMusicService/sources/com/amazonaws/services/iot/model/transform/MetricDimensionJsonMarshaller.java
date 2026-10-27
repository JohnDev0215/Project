package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricDimension;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class MetricDimensionJsonMarshaller {
    private static MetricDimensionJsonMarshaller instance;

    MetricDimensionJsonMarshaller() {
    }

    public void marshall(MetricDimension metricDimension, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricDimension.getDimensionName() != null) {
            String dimensionName = metricDimension.getDimensionName();
            awsJsonWriter.name("dimensionName");
            awsJsonWriter.value(dimensionName);
        }
        if (metricDimension.getOperator() != null) {
            String operator = metricDimension.getOperator();
            awsJsonWriter.name("operator");
            awsJsonWriter.value(operator);
        }
        awsJsonWriter.endObject();
    }

    public static MetricDimensionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricDimensionJsonMarshaller();
        }
        return instance;
    }
}
