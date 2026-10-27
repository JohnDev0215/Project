package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TransferCertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class TransferCertificateResultJsonUnmarshaller implements Unmarshaller<TransferCertificateResult, JsonUnmarshallerContext> {
    private static TransferCertificateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public TransferCertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        TransferCertificateResult transferCertificateResult = new TransferCertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("transferredCertificateArn")) {
                transferCertificateResult.setTransferredCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return transferCertificateResult;
    }

    public static TransferCertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TransferCertificateResultJsonUnmarshaller();
        }
        return instance;
    }
}
