package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.CACertificate;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class CACertificateJsonMarshaller {
    private static CACertificateJsonMarshaller instance;

    CACertificateJsonMarshaller() {
    }

    public void marshall(CACertificate cACertificate, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (cACertificate.getCertificateArn() != null) {
            String certificateArn = cACertificate.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (cACertificate.getCertificateId() != null) {
            String certificateId = cACertificate.getCertificateId();
            awsJsonWriter.name("certificateId");
            awsJsonWriter.value(certificateId);
        }
        if (cACertificate.getStatus() != null) {
            String status = cACertificate.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (cACertificate.getCreationDate() != null) {
            Date creationDate = cACertificate.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static CACertificateJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CACertificateJsonMarshaller();
        }
        return instance;
    }
}
