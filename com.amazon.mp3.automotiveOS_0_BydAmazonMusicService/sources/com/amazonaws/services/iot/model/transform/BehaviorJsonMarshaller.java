package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.services.iot.model.MetricDimension;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorJsonMarshaller {
    private static BehaviorJsonMarshaller instance;

    BehaviorJsonMarshaller() {
    }

    public void marshall(Behavior behavior, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (behavior.getName() != null) {
            String name = behavior.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (behavior.getMetric() != null) {
            String metric = behavior.getMetric();
            awsJsonWriter.name("metric");
            awsJsonWriter.value(metric);
        }
        if (behavior.getMetricDimension() != null) {
            MetricDimension metricDimension = behavior.getMetricDimension();
            awsJsonWriter.name("metricDimension");
            MetricDimensionJsonMarshaller.getInstance().marshall(metricDimension, awsJsonWriter);
        }
        if (behavior.getCriteria() != null) {
            BehaviorCriteria criteria = behavior.getCriteria();
            awsJsonWriter.name("criteria");
            BehaviorCriteriaJsonMarshaller.getInstance().marshall(criteria, awsJsonWriter);
        }
        if (behavior.getSuppressAlerts() != null) {
            Boolean suppressAlerts = behavior.getSuppressAlerts();
            awsJsonWriter.name("suppressAlerts");
            awsJsonWriter.value(suppressAlerts.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static BehaviorJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorJsonMarshaller();
        }
        return instance;
    }
}
