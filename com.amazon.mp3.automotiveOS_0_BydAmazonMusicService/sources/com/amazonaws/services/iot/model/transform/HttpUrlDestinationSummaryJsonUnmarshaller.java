package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationSummaryJsonUnmarshaller implements Unmarshaller<HttpUrlDestinationSummary, JsonUnmarshallerContext> {
    private static HttpUrlDestinationSummaryJsonUnmarshaller instance;

    HttpUrlDestinationSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpUrlDestinationSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpUrlDestinationSummary httpUrlDestinationSummary = new HttpUrlDestinationSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("confirmationUrl")) {
                httpUrlDestinationSummary.setConfirmationUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpUrlDestinationSummary;
    }

    public static HttpUrlDestinationSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
