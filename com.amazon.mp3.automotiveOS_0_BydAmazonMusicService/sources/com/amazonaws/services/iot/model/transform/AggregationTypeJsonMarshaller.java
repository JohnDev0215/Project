package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AggregationType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AggregationTypeJsonMarshaller {
    private static AggregationTypeJsonMarshaller instance;

    AggregationTypeJsonMarshaller() {
    }

    public void marshall(AggregationType aggregationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (aggregationType.getName() != null) {
            String name = aggregationType.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (aggregationType.getValues() != null) {
            List<String> values = aggregationType.getValues();
            awsJsonWriter.name("values");
            awsJsonWriter.beginArray();
            for (String str : values) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static AggregationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AggregationTypeJsonMarshaller();
        }
        return instance;
    }
}
