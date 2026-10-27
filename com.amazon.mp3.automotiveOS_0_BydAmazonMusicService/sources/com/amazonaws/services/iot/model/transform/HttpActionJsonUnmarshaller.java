package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes5.dex */
class HttpActionJsonUnmarshaller implements Unmarshaller<HttpAction, JsonUnmarshallerContext> {
    private static HttpActionJsonUnmarshaller instance;

    HttpActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public HttpAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        HttpAction httpAction = new HttpAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals(ImagesContract.URL)) {
                httpAction.setUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("confirmationUrl")) {
                httpAction.setConfirmationUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("headers")) {
                httpAction.setHeaders(new ListUnmarshaller(HttpActionHeaderJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("auth")) {
                httpAction.setAuth(HttpAuthorizationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return httpAction;
    }

    public static HttpActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new HttpActionJsonUnmarshaller();
        }
        return instance;
    }
}
