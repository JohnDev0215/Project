package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileTarget;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class SecurityProfileTargetJsonMarshaller {
    private static SecurityProfileTargetJsonMarshaller instance;

    SecurityProfileTargetJsonMarshaller() {
    }

    public void marshall(SecurityProfileTarget securityProfileTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (securityProfileTarget.getArn() != null) {
            String arn = securityProfileTarget.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        awsJsonWriter.endObject();
    }

    public static SecurityProfileTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileTargetJsonMarshaller();
        }
        return instance;
    }
}
