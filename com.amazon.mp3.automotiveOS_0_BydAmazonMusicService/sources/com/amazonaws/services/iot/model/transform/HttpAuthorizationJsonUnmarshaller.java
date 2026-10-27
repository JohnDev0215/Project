package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpAuthorization;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpAuthorizationJsonUnmarshaller implements Unmarshaller<HttpAuthorization, JsonUnmarshallerContext> {
    private static HttpAuthorizationJsonUnmarshaller instance;

    HttpAuthorizationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpAuthorization unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpAuthorization httpAuthorization = new HttpAuthorization();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("sigv4")) {
                httpAuthorization.setSigv4(SigV4AuthorizationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpAuthorization;
    }

    public static HttpAuthorizationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpAuthorizationJsonUnmarshaller();
        }
        return instance;
    }
}
