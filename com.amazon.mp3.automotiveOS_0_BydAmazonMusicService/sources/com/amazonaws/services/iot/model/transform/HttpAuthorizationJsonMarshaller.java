package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpAuthorization;
import com.amazonaws.services.iot.model.SigV4Authorization;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class HttpAuthorizationJsonMarshaller {
    private static HttpAuthorizationJsonMarshaller instance;

    HttpAuthorizationJsonMarshaller() {
    }

    public void marshall(HttpAuthorization httpAuthorization, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (httpAuthorization.getSigv4() != null) {
            SigV4Authorization sigv4 = httpAuthorization.getSigv4();
            awsJsonWriter.name("sigv4");
            SigV4AuthorizationJsonMarshaller.getInstance().marshall(sigv4, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static HttpAuthorizationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new HttpAuthorizationJsonMarshaller();
        }
        return instance;
    }
}
