package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditSuppression;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class AuditSuppressionJsonMarshaller {
    private static AuditSuppressionJsonMarshaller instance;

    AuditSuppressionJsonMarshaller() {
    }

    public void marshall(AuditSuppression auditSuppression, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditSuppression.getCheckName() != null) {
            String checkName = auditSuppression.getCheckName();
            awsJsonWriter.name("checkName");
            awsJsonWriter.value(checkName);
        }
        if (auditSuppression.getResourceIdentifier() != null) {
            ResourceIdentifier resourceIdentifier = auditSuppression.getResourceIdentifier();
            awsJsonWriter.name("resourceIdentifier");
            ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, awsJsonWriter);
        }
        if (auditSuppression.getExpirationDate() != null) {
            Date expirationDate = auditSuppression.getExpirationDate();
            awsJsonWriter.name("expirationDate");
            awsJsonWriter.value(expirationDate);
        }
        if (auditSuppression.getSuppressIndefinitely() != null) {
            Boolean suppressIndefinitely = auditSuppression.getSuppressIndefinitely();
            awsJsonWriter.name("suppressIndefinitely");
            awsJsonWriter.value(suppressIndefinitely.booleanValue());
        }
        if (auditSuppression.getDescription() != null) {
            String description = auditSuppression.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        awsJsonWriter.endObject();
    }

    public static AuditSuppressionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditSuppressionJsonMarshaller();
        }
        return instance;
    }
}
