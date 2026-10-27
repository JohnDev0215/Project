package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SalesforceAction;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes5.dex */
class SalesforceActionJsonMarshaller {
    private static SalesforceActionJsonMarshaller instance;

    SalesforceActionJsonMarshaller() {
    }

    public void marshall(SalesforceAction salesforceAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (salesforceAction.getToken() != null) {
            String token = salesforceAction.getToken();
            awsJsonWriter.name("token");
            awsJsonWriter.value(token);
        }
        if (salesforceAction.getUrl() != null) {
            String url = salesforceAction.getUrl();
            awsJsonWriter.name(ImagesContract.URL);
            awsJsonWriter.value(url);
        }
        awsJsonWriter.endObject();
    }

    public static SalesforceActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SalesforceActionJsonMarshaller();
        }
        return instance;
    }
}
