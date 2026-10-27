package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpActionHeader;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class HttpActionHeaderJsonMarshaller {
    private static HttpActionHeaderJsonMarshaller instance;

    HttpActionHeaderJsonMarshaller() {
    }

    public void marshall(HttpActionHeader httpActionHeader, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpActionHeader.getKey() != null) {
            String key = httpActionHeader.getKey();
            awsJsonWriter.name("key");
            awsJsonWriter.value(key);
        }
        if (httpActionHeader.getValue() != null) {
            String value = httpActionHeader.getValue();
            awsJsonWriter.name("value");
            awsJsonWriter.value(value);
        }
        awsJsonWriter.endObject();
    }

    public static HttpActionHeaderJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpActionHeaderJsonMarshaller();
        }
        return instance;
    }
}
