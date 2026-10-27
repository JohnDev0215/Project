package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CustomCodeSigning;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CustomCodeSigningJsonUnmarshaller implements Unmarshaller<CustomCodeSigning, JsonUnmarshallerContext> {
    private static CustomCodeSigningJsonUnmarshaller instance;

    CustomCodeSigningJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CustomCodeSigning unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CustomCodeSigning customCodeSigning = new CustomCodeSigning();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("signature")) {
                customCodeSigning.setSignature(CodeSigningSignatureJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateChain")) {
                customCodeSigning.setCertificateChain(CodeSigningCertificateChainJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("hashAlgorithm")) {
                customCodeSigning.setHashAlgorithm(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("signatureAlgorithm")) {
                customCodeSigning.setSignatureAlgorithm(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return customCodeSigning;
    }

    public static CustomCodeSigningJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CustomCodeSigningJsonUnmarshaller();
        }
        return instance;
    }
}
