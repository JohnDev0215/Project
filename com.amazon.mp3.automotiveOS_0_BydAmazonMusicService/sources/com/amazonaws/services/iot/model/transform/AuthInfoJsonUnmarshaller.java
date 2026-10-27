package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuthInfoJsonUnmarshaller implements Unmarshaller<AuthInfo, JsonUnmarshallerContext> {
    private static AuthInfoJsonUnmarshaller instance;

    AuthInfoJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthInfo authInfo = new AuthInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionType")) {
                authInfo.setActionType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("resources")) {
                authInfo.setResources(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authInfo;
    }

    public static AuthInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthInfoJsonUnmarshaller();
        }
        return instance;
    }
}
