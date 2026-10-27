package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationConfigurationJsonMarshaller {
    private static HttpUrlDestinationConfigurationJsonMarshaller instance;

    HttpUrlDestinationConfigurationJsonMarshaller() {
    }

    public void marshall(HttpUrlDestinationConfiguration httpUrlDestinationConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpUrlDestinationConfiguration.getConfirmationUrl() != null) {
            String confirmationUrl = httpUrlDestinationConfiguration.getConfirmationUrl();
            awsJsonWriter.name("confirmationUrl");
            awsJsonWriter.value(confirmationUrl);
        }
        awsJsonWriter.endObject();
    }

    public static HttpUrlDestinationConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationConfigurationJsonMarshaller();
        }
        return instance;
    }
}
