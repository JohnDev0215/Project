package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimestreamDimension;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamDimensionJsonMarshaller {
    private static TimestreamDimensionJsonMarshaller instance;

    TimestreamDimensionJsonMarshaller() {
    }

    public void marshall(TimestreamDimension timestreamDimension, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (timestreamDimension.getName() != null) {
            String name = timestreamDimension.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (timestreamDimension.getValue() != null) {
            String value = timestreamDimension.getValue();
            awsJsonWriter.name("value");
            awsJsonWriter.value(value);
        }
        awsJsonWriter.endObject();
    }

    public static TimestreamDimensionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamDimensionJsonMarshaller();
        }
        return instance;
    }
}
