package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationProperties;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationPropertiesJsonMarshaller {
    private static HttpUrlDestinationPropertiesJsonMarshaller instance;

    HttpUrlDestinationPropertiesJsonMarshaller() {
    }

    public void marshall(HttpUrlDestinationProperties httpUrlDestinationProperties, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpUrlDestinationProperties.getConfirmationUrl() != null) {
            String confirmationUrl = httpUrlDestinationProperties.getConfirmationUrl();
            awsJsonWriter.name("confirmationUrl");
            awsJsonWriter.value(confirmationUrl);
        }
        awsJsonWriter.endObject();
    }

    public static HttpUrlDestinationPropertiesJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationPropertiesJsonMarshaller();
        }
        return instance;
    }
}
