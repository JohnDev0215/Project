package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.AuthorizerDescription;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuthorizerDescriptionJsonUnmarshaller implements Unmarshaller<AuthorizerDescription, JsonUnmarshallerContext> {
    private static AuthorizerDescriptionJsonUnmarshaller instance;

    AuthorizerDescriptionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthorizerDescription unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthorizerDescription authorizerDescription = new AuthorizerDescription();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("authorizerName")) {
                authorizerDescription.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("authorizerArn")) {
                authorizerDescription.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("authorizerFunctionArn")) {
                authorizerDescription.setAuthorizerFunctionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("tokenKeyName")) {
                authorizerDescription.setTokenKeyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("tokenSigningPublicKeys")) {
                authorizerDescription.setTokenSigningPublicKeys(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                authorizerDescription.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                authorizerDescription.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                authorizerDescription.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("signingDisabled")) {
                authorizerDescription.setSigningDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("enableCachingForHttp")) {
                authorizerDescription.setEnableCachingForHttp(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authorizerDescription;
    }

    public static AuthorizerDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerDescriptionJsonUnmarshaller();
        }
        return instance;
    }
}
