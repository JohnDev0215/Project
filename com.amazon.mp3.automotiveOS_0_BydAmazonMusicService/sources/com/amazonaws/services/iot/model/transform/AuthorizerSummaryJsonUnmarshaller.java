package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthorizerSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuthorizerSummaryJsonUnmarshaller implements Unmarshaller<AuthorizerSummary, JsonUnmarshallerContext> {
    private static AuthorizerSummaryJsonUnmarshaller instance;

    AuthorizerSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthorizerSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthorizerSummary authorizerSummary = new AuthorizerSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("authorizerName")) {
                authorizerSummary.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("authorizerArn")) {
                authorizerSummary.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authorizerSummary;
    }

    public static AuthorizerSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
