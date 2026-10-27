package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricDimension;
import com.amazonaws.services.iot.model.MetricToRetain;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class MetricToRetainJsonMarshaller {
    private static MetricToRetainJsonMarshaller instance;

    MetricToRetainJsonMarshaller() {
    }

    public void marshall(MetricToRetain metricToRetain, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricToRetain.getMetric() != null) {
            String metric = metricToRetain.getMetric();
            awsJsonWriter.name("metric");
            awsJsonWriter.value(metric);
        }
        if (metricToRetain.getMetricDimension() != null) {
            MetricDimension metricDimension = metricToRetain.getMetricDimension();
            awsJsonWriter.name("metricDimension");
            MetricDimensionJsonMarshaller.getInstance().marshall(metricDimension, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static MetricToRetainJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricToRetainJsonMarshaller();
        }
        return instance;
    }
}
