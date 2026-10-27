package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.FleetMetricNameAndArn;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class FleetMetricNameAndArnJsonMarshaller {
    private static FleetMetricNameAndArnJsonMarshaller instance;

    FleetMetricNameAndArnJsonMarshaller() {
    }

    public void marshall(FleetMetricNameAndArn fleetMetricNameAndArn, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (fleetMetricNameAndArn.getMetricName() != null) {
            String metricName = fleetMetricNameAndArn.getMetricName();
            awsJsonWriter.name("metricName");
            awsJsonWriter.value(metricName);
        }
        if (fleetMetricNameAndArn.getMetricArn() != null) {
            String metricArn = fleetMetricNameAndArn.getMetricArn();
            awsJsonWriter.name("metricArn");
            awsJsonWriter.value(metricArn);
        }
        awsJsonWriter.endObject();
    }

    public static FleetMetricNameAndArnJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new FleetMetricNameAndArnJsonMarshaller();
        }
        return instance;
    }
}
