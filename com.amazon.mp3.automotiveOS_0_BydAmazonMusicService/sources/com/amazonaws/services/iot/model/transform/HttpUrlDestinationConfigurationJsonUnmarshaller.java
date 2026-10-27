package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationConfigurationJsonUnmarshaller implements Unmarshaller<HttpUrlDestinationConfiguration, JsonUnmarshallerContext> {
    private static HttpUrlDestinationConfigurationJsonUnmarshaller instance;

    HttpUrlDestinationConfigurationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpUrlDestinationConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpUrlDestinationConfiguration httpUrlDestinationConfiguration = new HttpUrlDestinationConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("confirmationUrl")) {
                httpUrlDestinationConfiguration.setConfirmationUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpUrlDestinationConfiguration;
    }

    public static HttpUrlDestinationConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
