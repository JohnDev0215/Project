package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpActionHeader;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpActionHeaderJsonUnmarshaller implements Unmarshaller<HttpActionHeader, JsonUnmarshallerContext> {
    private static HttpActionHeaderJsonUnmarshaller instance;

    HttpActionHeaderJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpActionHeader unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpActionHeader httpActionHeader = new HttpActionHeader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("key")) {
                httpActionHeader.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("value")) {
                httpActionHeader.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpActionHeader;
    }

    public static HttpActionHeaderJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpActionHeaderJsonUnmarshaller();
        }
        return instance;
    }
}
