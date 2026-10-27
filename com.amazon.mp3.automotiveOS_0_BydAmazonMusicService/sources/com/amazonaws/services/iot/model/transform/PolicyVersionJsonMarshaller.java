package com.amazonaws.services.iot.model.transform;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import com.amazonaws.services.iot.model.PolicyVersion;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class PolicyVersionJsonMarshaller {
    private static PolicyVersionJsonMarshaller instance;

    PolicyVersionJsonMarshaller() {
    }

    public void marshall(PolicyVersion policyVersion, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (policyVersion.getVersionId() != null) {
            String versionId = policyVersion.getVersionId();
            awsJsonWriter.name("versionId");
            awsJsonWriter.value(versionId);
        }
        if (policyVersion.getIsDefaultVersion() != null) {
            Boolean isDefaultVersion = policyVersion.getIsDefaultVersion();
            awsJsonWriter.name("isDefaultVersion");
            awsJsonWriter.value(isDefaultVersion.booleanValue());
        }
        if (policyVersion.getCreateDate() != null) {
            Date createDate = policyVersion.getCreateDate();
            awsJsonWriter.name(OAuth2Client.CREATE_DATE);
            awsJsonWriter.value(createDate);
        }
        awsJsonWriter.endObject();
    }

    public static PolicyVersionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyVersionJsonMarshaller();
        }
        return instance;
    }
}
