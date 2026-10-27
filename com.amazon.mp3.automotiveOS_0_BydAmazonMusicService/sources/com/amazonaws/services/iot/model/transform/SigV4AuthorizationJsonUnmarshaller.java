package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SigV4Authorization;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SigV4AuthorizationJsonUnmarshaller implements Unmarshaller<SigV4Authorization, JsonUnmarshallerContext> {
    private static SigV4AuthorizationJsonUnmarshaller instance;

    SigV4AuthorizationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SigV4Authorization unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SigV4Authorization sigV4Authorization = new SigV4Authorization();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("signingRegion")) {
                sigV4Authorization.setSigningRegion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serviceName")) {
                sigV4Authorization.setServiceName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                sigV4Authorization.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return sigV4Authorization;
    }

    public static SigV4AuthorizationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SigV4AuthorizationJsonUnmarshaller();
        }
        return instance;
    }
}
