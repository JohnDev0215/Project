package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationSummary;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationSummaryJsonMarshaller {
    private static HttpUrlDestinationSummaryJsonMarshaller instance;

    HttpUrlDestinationSummaryJsonMarshaller() {
    }

    public void marshall(HttpUrlDestinationSummary httpUrlDestinationSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpUrlDestinationSummary.getConfirmationUrl() != null) {
            String confirmationUrl = httpUrlDestinationSummary.getConfirmationUrl();
            awsJsonWriter.name("confirmationUrl");
            awsJsonWriter.value(confirmationUrl);
        }
        awsJsonWriter.endObject();
    }

    public static HttpUrlDestinationSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationSummaryJsonMarshaller();
        }
        return instance;
    }
}
