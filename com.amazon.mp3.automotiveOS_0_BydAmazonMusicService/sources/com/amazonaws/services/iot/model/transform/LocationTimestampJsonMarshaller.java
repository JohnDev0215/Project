package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LocationTimestamp;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class LocationTimestampJsonMarshaller {
    private static LocationTimestampJsonMarshaller instance;

    LocationTimestampJsonMarshaller() {
    }

    public void marshall(LocationTimestamp locationTimestamp, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (locationTimestamp.getValue() != null) {
            String value = locationTimestamp.getValue();
            awsJsonWriter.name("value");
            awsJsonWriter.value(value);
        }
        if (locationTimestamp.getUnit() != null) {
            String unit = locationTimestamp.getUnit();
            awsJsonWriter.name("unit");
            awsJsonWriter.value(unit);
        }
        awsJsonWriter.endObject();
    }

    public static LocationTimestampJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LocationTimestampJsonMarshaller();
        }
        return instance;
    }
}
