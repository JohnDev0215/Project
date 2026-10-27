package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class MetricValueJsonMarshaller {
    private static MetricValueJsonMarshaller instance;

    MetricValueJsonMarshaller() {
    }

    public void marshall(MetricValue metricValue, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricValue.getCount() != null) {
            Long count = metricValue.getCount();
            awsJsonWriter.name("count");
            awsJsonWriter.value(count);
        }
        if (metricValue.getCidrs() != null) {
            List<String> cidrs = metricValue.getCidrs();
            awsJsonWriter.name("cidrs");
            awsJsonWriter.beginArray();
            for (String str : cidrs) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (metricValue.getPorts() != null) {
            List<Integer> ports = metricValue.getPorts();
            awsJsonWriter.name("ports");
            awsJsonWriter.beginArray();
            for (Integer num : ports) {
                if (num != null) {
                    awsJsonWriter.value(num);
                }
            }
            awsJsonWriter.endArray();
        }
        if (metricValue.getNumber() != null) {
            Double number = metricValue.getNumber();
            awsJsonWriter.name("number");
            awsJsonWriter.value(number);
        }
        if (metricValue.getNumbers() != null) {
            List<Double> numbers = metricValue.getNumbers();
            awsJsonWriter.name("numbers");
            awsJsonWriter.beginArray();
            for (Double d : numbers) {
                if (d != null) {
                    awsJsonWriter.value(d);
                }
            }
            awsJsonWriter.endArray();
        }
        if (metricValue.getStrings() != null) {
            List<String> strings = metricValue.getStrings();
            awsJsonWriter.name("strings");
            awsJsonWriter.beginArray();
            for (String str2 : strings) {
                if (str2 != null) {
                    awsJsonWriter.value(str2);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static MetricValueJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricValueJsonMarshaller();
        }
        return instance;
    }
}
