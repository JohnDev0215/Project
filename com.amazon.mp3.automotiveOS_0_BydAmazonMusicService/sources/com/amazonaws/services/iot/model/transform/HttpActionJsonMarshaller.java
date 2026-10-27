package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpAction;
import com.amazonaws.services.iot.model.HttpActionHeader;
import com.amazonaws.services.iot.model.HttpAuthorization;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class HttpActionJsonMarshaller {
    private static HttpActionJsonMarshaller instance;

    HttpActionJsonMarshaller() {
    }

    public void marshall(HttpAction httpAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpAction.getUrl() != null) {
            String url = httpAction.getUrl();
            awsJsonWriter.name(ImagesContract.URL);
            awsJsonWriter.value(url);
        }
        if (httpAction.getConfirmationUrl() != null) {
            String confirmationUrl = httpAction.getConfirmationUrl();
            awsJsonWriter.name("confirmationUrl");
            awsJsonWriter.value(confirmationUrl);
        }
        if (httpAction.getHeaders() != null) {
            List<HttpActionHeader> headers = httpAction.getHeaders();
            awsJsonWriter.name("headers");
            awsJsonWriter.beginArray();
            for (HttpActionHeader httpActionHeader : headers) {
                if (httpActionHeader != null) {
                    HttpActionHeaderJsonMarshaller.getInstance().marshall(httpActionHeader, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (httpAction.getAuth() != null) {
            HttpAuthorization auth = httpAction.getAuth();
            awsJsonWriter.name("auth");
            HttpAuthorizationJsonMarshaller.getInstance().marshall(auth, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static HttpActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpActionJsonMarshaller();
        }
        return instance;
    }
}
