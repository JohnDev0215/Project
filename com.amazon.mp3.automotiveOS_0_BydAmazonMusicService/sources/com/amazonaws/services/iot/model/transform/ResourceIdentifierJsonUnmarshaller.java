package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ResourceIdentifierJsonUnmarshaller implements Unmarshaller<ResourceIdentifier, JsonUnmarshallerContext> {
    private static ResourceIdentifierJsonUnmarshaller instance;

    ResourceIdentifierJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ResourceIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ResourceIdentifier resourceIdentifier = new ResourceIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("deviceCertificateId")) {
                resourceIdentifier.setDeviceCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("caCertificateId")) {
                resourceIdentifier.setCaCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("cognitoIdentityPoolId")) {
                resourceIdentifier.setCognitoIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("clientId")) {
                resourceIdentifier.setClientId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyVersionIdentifier")) {
                resourceIdentifier.setPolicyVersionIdentifier(PolicyVersionIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("account")) {
                resourceIdentifier.setAccount(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("iamRoleArn")) {
                resourceIdentifier.setIamRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleAliasArn")) {
                resourceIdentifier.setRoleAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("issuerCertificateIdentifier")) {
                resourceIdentifier.setIssuerCertificateIdentifier(IssuerCertificateIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("deviceCertificateArn")) {
                resourceIdentifier.setDeviceCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return resourceIdentifier;
    }

    public static ResourceIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ResourceIdentifierJsonUnmarshaller();
        }
        return instance;
    }
}
