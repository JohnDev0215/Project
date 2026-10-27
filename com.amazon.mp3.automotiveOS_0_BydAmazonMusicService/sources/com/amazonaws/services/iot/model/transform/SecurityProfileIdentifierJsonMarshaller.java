package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class SecurityProfileIdentifierJsonMarshaller {
    private static SecurityProfileIdentifierJsonMarshaller instance;

    SecurityProfileIdentifierJsonMarshaller() {
    }

    public void marshall(SecurityProfileIdentifier securityProfileIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (securityProfileIdentifier.getName() != null) {
            String name = securityProfileIdentifier.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (securityProfileIdentifier.getArn() != null) {
            String arn = securityProfileIdentifier.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        awsJsonWriter.endObject();
    }

    public static SecurityProfileIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileIdentifierJsonMarshaller();
        }
        return instance;
    }
}
