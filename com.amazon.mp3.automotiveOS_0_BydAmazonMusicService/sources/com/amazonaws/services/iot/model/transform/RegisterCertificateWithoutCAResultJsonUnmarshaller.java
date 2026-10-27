package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegisterCertificateWithoutCAResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateWithoutCAResultJsonUnmarshaller implements Unmarshaller<RegisterCertificateWithoutCAResult, JsonUnmarshallerContext> {
    private static RegisterCertificateWithoutCAResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public RegisterCertificateWithoutCAResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        RegisterCertificateWithoutCAResult registerCertificateWithoutCAResult = new RegisterCertificateWithoutCAResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateArn")) {
                registerCertificateWithoutCAResult.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateId")) {
                registerCertificateWithoutCAResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registerCertificateWithoutCAResult;
    }

    public static RegisterCertificateWithoutCAResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegisterCertificateWithoutCAResultJsonUnmarshaller();
        }
        return instance;
    }
}
