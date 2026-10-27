package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IssuerCertificateIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class IssuerCertificateIdentifierJsonMarshaller {
    private static IssuerCertificateIdentifierJsonMarshaller instance;

    IssuerCertificateIdentifierJsonMarshaller() {
    }

    public void marshall(IssuerCertificateIdentifier issuerCertificateIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (issuerCertificateIdentifier.getIssuerCertificateSubject() != null) {
            String issuerCertificateSubject = issuerCertificateIdentifier.getIssuerCertificateSubject();
            awsJsonWriter.name("issuerCertificateSubject");
            awsJsonWriter.value(issuerCertificateSubject);
        }
        if (issuerCertificateIdentifier.getIssuerId() != null) {
            String issuerId = issuerCertificateIdentifier.getIssuerId();
            awsJsonWriter.name("issuerId");
            awsJsonWriter.value(issuerId);
        }
        if (issuerCertificateIdentifier.getIssuerCertificateSerialNumber() != null) {
            String issuerCertificateSerialNumber = issuerCertificateIdentifier.getIssuerCertificateSerialNumber();
            awsJsonWriter.name("issuerCertificateSerialNumber");
            awsJsonWriter.value(issuerCertificateSerialNumber);
        }
        awsJsonWriter.endObject();
    }

    public static IssuerCertificateIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IssuerCertificateIdentifierJsonMarshaller();
        }
        return instance;
    }
}
