package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationProperties;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpUrlDestinationPropertiesJsonUnmarshaller implements Unmarshaller<HttpUrlDestinationProperties, JsonUnmarshallerContext> {
    private static HttpUrlDestinationPropertiesJsonUnmarshaller instance;

    HttpUrlDestinationPropertiesJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpUrlDestinationProperties unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpUrlDestinationProperties httpUrlDestinationProperties = new HttpUrlDestinationProperties();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("confirmationUrl")) {
                httpUrlDestinationProperties.setConfirmationUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpUrlDestinationProperties;
    }

    public static HttpUrlDestinationPropertiesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpUrlDestinationPropertiesJsonUnmarshaller();
        }
        return instance;
    }
}
