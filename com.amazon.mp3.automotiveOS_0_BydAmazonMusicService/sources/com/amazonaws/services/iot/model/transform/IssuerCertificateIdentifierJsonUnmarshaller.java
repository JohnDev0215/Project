package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IssuerCertificateIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class IssuerCertificateIdentifierJsonUnmarshaller implements Unmarshaller<IssuerCertificateIdentifier, JsonUnmarshallerContext> {
    private static IssuerCertificateIdentifierJsonUnmarshaller instance;

    IssuerCertificateIdentifierJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IssuerCertificateIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IssuerCertificateIdentifier issuerCertificateIdentifier = new IssuerCertificateIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("issuerCertificateSubject")) {
                issuerCertificateIdentifier.setIssuerCertificateSubject(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("issuerId")) {
                issuerCertificateIdentifier.setIssuerId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("issuerCertificateSerialNumber")) {
                issuerCertificateIdentifier.setIssuerCertificateSerialNumber(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return issuerCertificateIdentifier;
    }

    public static IssuerCertificateIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IssuerCertificateIdentifierJsonUnmarshaller();
        }
        return instance;
    }
}
