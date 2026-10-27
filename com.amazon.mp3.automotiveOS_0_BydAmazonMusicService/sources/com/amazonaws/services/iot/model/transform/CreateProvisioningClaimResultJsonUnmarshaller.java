package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateProvisioningClaimResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningClaimResultJsonUnmarshaller implements Unmarshaller<CreateProvisioningClaimResult, JsonUnmarshallerContext> {
    private static CreateProvisioningClaimResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateProvisioningClaimResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateProvisioningClaimResult createProvisioningClaimResult = new CreateProvisioningClaimResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateId")) {
                createProvisioningClaimResult.setCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("certificatePem")) {
                createProvisioningClaimResult.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("keyPair")) {
                createProvisioningClaimResult.setKeyPair(KeyPairJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("expiration")) {
                createProvisioningClaimResult.setExpiration(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createProvisioningClaimResult;
    }

    public static CreateProvisioningClaimResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateProvisioningClaimResultJsonUnmarshaller();
        }
        return instance;
    }
}
