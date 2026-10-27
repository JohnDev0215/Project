package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegisterCertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateResultJsonUnmarshaller implements Unmarshaller<RegisterCertificateResult, JsonUnmarshallerContext> {
    private static RegisterCertificateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public RegisterCertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        RegisterCertificateResult registerCertificateResult = new RegisterCertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateArn")) {
                registerCertificateResult.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateId")) {
                registerCertificateResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registerCertificateResult;
    }

    public static RegisterCertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegisterCertificateResultJsonUnmarshaller();
        }
        return instance;
    }
}
