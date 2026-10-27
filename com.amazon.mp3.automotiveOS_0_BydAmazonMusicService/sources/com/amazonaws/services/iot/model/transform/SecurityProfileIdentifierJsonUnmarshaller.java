package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SecurityProfileIdentifierJsonUnmarshaller implements Unmarshaller<SecurityProfileIdentifier, JsonUnmarshallerContext> {
    private static SecurityProfileIdentifierJsonUnmarshaller instance;

    SecurityProfileIdentifierJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SecurityProfileIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SecurityProfileIdentifier securityProfileIdentifier = new SecurityProfileIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                securityProfileIdentifier.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("arn")) {
                securityProfileIdentifier.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return securityProfileIdentifier;
    }

    public static SecurityProfileIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileIdentifierJsonUnmarshaller();
        }
        return instance;
    }
}
