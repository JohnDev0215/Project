package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpContext;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class HttpContextJsonUnmarshaller implements Unmarshaller<HttpContext, JsonUnmarshallerContext> {
    private static HttpContextJsonUnmarshaller instance;

    HttpContextJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpContext unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpContext httpContext = new HttpContext();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("headers")) {
                httpContext.setHeaders(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryString")) {
                httpContext.setQueryString(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpContext;
    }

    public static HttpContextJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpContextJsonUnmarshaller();
        }
        return instance;
    }
}
