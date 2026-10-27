package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimestreamTimestamp;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamTimestampJsonMarshaller {
    private static TimestreamTimestampJsonMarshaller instance;

    TimestreamTimestampJsonMarshaller() {
    }

    public void marshall(TimestreamTimestamp timestreamTimestamp, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (timestreamTimestamp.getValue() != null) {
            String value = timestreamTimestamp.getValue();
            awsJsonWriter.name("value");
            awsJsonWriter.value(value);
        }
        if (timestreamTimestamp.getUnit() != null) {
            String unit = timestreamTimestamp.getUnit();
            awsJsonWriter.name("unit");
            awsJsonWriter.value(unit);
        }
        awsJsonWriter.endObject();
    }

    public static TimestreamTimestampJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamTimestampJsonMarshaller();
        }
        return instance;
    }
}
