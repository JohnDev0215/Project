package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthorizerSummary;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AuthorizerSummaryJsonMarshaller {
    private static AuthorizerSummaryJsonMarshaller instance;

    AuthorizerSummaryJsonMarshaller() {
    }

    public void marshall(AuthorizerSummary authorizerSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authorizerSummary.getAuthorizerName() != null) {
            String authorizerName = authorizerSummary.getAuthorizerName();
            awsJsonWriter.name("authorizerName");
            awsJsonWriter.value(authorizerName);
        }
        if (authorizerSummary.getAuthorizerArn() != null) {
            String authorizerArn = authorizerSummary.getAuthorizerArn();
            awsJsonWriter.name("authorizerArn");
            awsJsonWriter.value(authorizerArn);
        }
        awsJsonWriter.endObject();
    }

    public static AuthorizerSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerSummaryJsonMarshaller();
        }
        return instance;
    }
}
