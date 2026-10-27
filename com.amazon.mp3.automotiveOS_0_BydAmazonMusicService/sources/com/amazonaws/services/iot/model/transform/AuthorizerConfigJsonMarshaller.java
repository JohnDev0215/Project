package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthorizerConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AuthorizerConfigJsonMarshaller {
    private static AuthorizerConfigJsonMarshaller instance;

    AuthorizerConfigJsonMarshaller() {
    }

    public void marshall(AuthorizerConfig authorizerConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authorizerConfig.getDefaultAuthorizerName() != null) {
            String defaultAuthorizerName = authorizerConfig.getDefaultAuthorizerName();
            awsJsonWriter.name("defaultAuthorizerName");
            awsJsonWriter.value(defaultAuthorizerName);
        }
        if (authorizerConfig.getAllowAuthorizerOverride() != null) {
            Boolean allowAuthorizerOverride = authorizerConfig.getAllowAuthorizerOverride();
            awsJsonWriter.name("allowAuthorizerOverride");
            awsJsonWriter.value(allowAuthorizerOverride.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static AuthorizerConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerConfigJsonMarshaller();
        }
        return instance;
    }
}
