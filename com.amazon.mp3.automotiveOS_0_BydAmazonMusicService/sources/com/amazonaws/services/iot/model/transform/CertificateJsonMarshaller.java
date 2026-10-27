package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.Certificate;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class CertificateJsonMarshaller {
    private static CertificateJsonMarshaller instance;

    CertificateJsonMarshaller() {
    }

    public void marshall(Certificate certificate, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (certificate.getCertificateArn() != null) {
            String certificateArn = certificate.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (certificate.getCertificateId() != null) {
            String certificateId = certificate.getCertificateId();
            awsJsonWriter.name("certificateId");
            awsJsonWriter.value(certificateId);
        }
        if (certificate.getStatus() != null) {
            String status = certificate.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (certificate.getCertificateMode() != null) {
            String certificateMode = certificate.getCertificateMode();
            awsJsonWriter.name("certificateMode");
            awsJsonWriter.value(certificateMode);
        }
        if (certificate.getCreationDate() != null) {
            Date creationDate = certificate.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static CertificateJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CertificateJsonMarshaller();
        }
        return instance;
    }
}
