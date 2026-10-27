package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpContext;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class HttpContextJsonMarshaller {
    private static HttpContextJsonMarshaller instance;

    HttpContextJsonMarshaller() {
    }

    public void marshall(HttpContext httpContext, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpContext.getHeaders() != null) {
            Map<String, String> headers = httpContext.getHeaders();
            awsJsonWriter.name("headers");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (httpContext.getQueryString() != null) {
            String queryString = httpContext.getQueryString();
            awsJsonWriter.name("queryString");
            awsJsonWriter.value(queryString);
        }
        awsJsonWriter.endObject();
    }

    public static HttpContextJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpContextJsonMarshaller();
        }
        return instance;
    }
}
