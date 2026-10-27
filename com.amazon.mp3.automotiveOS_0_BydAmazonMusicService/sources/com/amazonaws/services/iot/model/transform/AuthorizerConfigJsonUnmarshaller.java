package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthorizerConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuthorizerConfigJsonUnmarshaller implements Unmarshaller<AuthorizerConfig, JsonUnmarshallerContext> {
    private static AuthorizerConfigJsonUnmarshaller instance;

    AuthorizerConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthorizerConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthorizerConfig authorizerConfig = new AuthorizerConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("defaultAuthorizerName")) {
                authorizerConfig.setDefaultAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("allowAuthorizerOverride")) {
                authorizerConfig.setAllowAuthorizerOverride(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authorizerConfig;
    }

    public static AuthorizerConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerConfigJsonUnmarshaller();
        }
        return instance;
    }
}
