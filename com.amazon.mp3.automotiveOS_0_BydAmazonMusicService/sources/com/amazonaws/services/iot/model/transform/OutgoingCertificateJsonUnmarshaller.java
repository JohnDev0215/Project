package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OutgoingCertificate;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class OutgoingCertificateJsonUnmarshaller implements Unmarshaller<OutgoingCertificate, JsonUnmarshallerContext> {
    private static OutgoingCertificateJsonUnmarshaller instance;

    OutgoingCertificateJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OutgoingCertificate unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OutgoingCertificate outgoingCertificate = new OutgoingCertificate();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateArn")) {
                outgoingCertificate.setCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificateId")) {
                outgoingCertificate.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("transferredTo")) {
                outgoingCertificate.setTransferredTo(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("transferDate")) {
                outgoingCertificate.setTransferDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("transferMessage")) {
                outgoingCertificate.setTransferMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                outgoingCertificate.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return outgoingCertificate;
    }

    public static OutgoingCertificateJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OutgoingCertificateJsonUnmarshaller();
        }
        return instance;
    }
}
