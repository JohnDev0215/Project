package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.MetricDatum;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class MetricDatumJsonMarshaller {
    private static MetricDatumJsonMarshaller instance;

    MetricDatumJsonMarshaller() {
    }

    public void marshall(MetricDatum metricDatum, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricDatum.getTimestamp() != null) {
            Date timestamp = metricDatum.getTimestamp();
            awsJsonWriter.name(TetheredMessageKey.timestamp);
            awsJsonWriter.value(timestamp);
        }
        if (metricDatum.getValue() != null) {
            MetricValue value = metricDatum.getValue();
            awsJsonWriter.name("value");
            MetricValueJsonMarshaller.getInstance().marshall(value, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static MetricDatumJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricDatumJsonMarshaller();
        }
        return instance;
    }
}
