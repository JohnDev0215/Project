package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IssuerCertificateIdentifier;
import com.amazonaws.services.iot.model.PolicyVersionIdentifier;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ResourceIdentifierJsonMarshaller {
    private static ResourceIdentifierJsonMarshaller instance;

    ResourceIdentifierJsonMarshaller() {
    }

    public void marshall(ResourceIdentifier resourceIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (resourceIdentifier.getDeviceCertificateId() != null) {
            String deviceCertificateId = resourceIdentifier.getDeviceCertificateId();
            awsJsonWriter.name("deviceCertificateId");
            awsJsonWriter.value(deviceCertificateId);
        }
        if (resourceIdentifier.getCaCertificateId() != null) {
            String caCertificateId = resourceIdentifier.getCaCertificateId();
            awsJsonWriter.name("caCertificateId");
            awsJsonWriter.value(caCertificateId);
        }
        if (resourceIdentifier.getCognitoIdentityPoolId() != null) {
            String cognitoIdentityPoolId = resourceIdentifier.getCognitoIdentityPoolId();
            awsJsonWriter.name("cognitoIdentityPoolId");
            awsJsonWriter.value(cognitoIdentityPoolId);
        }
        if (resourceIdentifier.getClientId() != null) {
            String clientId = resourceIdentifier.getClientId();
            awsJsonWriter.name("clientId");
            awsJsonWriter.value(clientId);
        }
        if (resourceIdentifier.getPolicyVersionIdentifier() != null) {
            PolicyVersionIdentifier policyVersionIdentifier = resourceIdentifier.getPolicyVersionIdentifier();
            awsJsonWriter.name("policyVersionIdentifier");
            PolicyVersionIdentifierJsonMarshaller.getInstance().marshall(policyVersionIdentifier, awsJsonWriter);
        }
        if (resourceIdentifier.getAccount() != null) {
            String account = resourceIdentifier.getAccount();
            awsJsonWriter.name("account");
            awsJsonWriter.value(account);
        }
        if (resourceIdentifier.getIamRoleArn() != null) {
            String iamRoleArn = resourceIdentifier.getIamRoleArn();
            awsJsonWriter.name("iamRoleArn");
            awsJsonWriter.value(iamRoleArn);
        }
        if (resourceIdentifier.getRoleAliasArn() != null) {
            String roleAliasArn = resourceIdentifier.getRoleAliasArn();
            awsJsonWriter.name("roleAliasArn");
            awsJsonWriter.value(roleAliasArn);
        }
        if (resourceIdentifier.getIssuerCertificateIdentifier() != null) {
            IssuerCertificateIdentifier issuerCertificateIdentifier = resourceIdentifier.getIssuerCertificateIdentifier();
            awsJsonWriter.name("issuerCertificateIdentifier");
            IssuerCertificateIdentifierJsonMarshaller.getInstance().marshall(issuerCertificateIdentifier, awsJsonWriter);
        }
        if (resourceIdentifier.getDeviceCertificateArn() != null) {
            String deviceCertificateArn = resourceIdentifier.getDeviceCertificateArn();
            awsJsonWriter.name("deviceCertificateArn");
            awsJsonWriter.value(deviceCertificateArn);
        }
        awsJsonWriter.endObject();
    }

    public static ResourceIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ResourceIdentifierJsonMarshaller();
        }
        return instance;
    }
}
