package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.CACertificateDescription;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CACertificateDescriptionJsonUnmarshaller implements Unmarshaller<CACertificateDescription, JsonUnmarshallerContext> {
    private static CACertificateDescriptionJsonUnmarshaller instance;

    CACertificateDescriptionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CACertificateDescription unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CACertificateDescription cACertificateDescription = new CACertificateDescription();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateArn")) {
                cACertificateDescription.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateId")) {
                cACertificateDescription.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                cACertificateDescription.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificatePem")) {
                cACertificateDescription.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ownedBy")) {
                cACertificateDescription.setOwnedBy(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                cACertificateDescription.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("autoRegistrationStatus")) {
                cACertificateDescription.setAutoRegistrationStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                cACertificateDescription.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("customerVersion")) {
                cACertificateDescription.setCustomerVersion(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("generationId")) {
                cACertificateDescription.setGenerationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("validity")) {
                cACertificateDescription.setValidity(CertificateValidityJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateMode")) {
                cACertificateDescription.setCertificateMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cACertificateDescription;
    }

    public static CACertificateDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CACertificateDescriptionJsonUnmarshaller();
        }
        return instance;
    }
}
