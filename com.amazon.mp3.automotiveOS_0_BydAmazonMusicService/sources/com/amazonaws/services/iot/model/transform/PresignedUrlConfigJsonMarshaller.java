package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class PresignedUrlConfigJsonMarshaller {
    private static PresignedUrlConfigJsonMarshaller instance;

    PresignedUrlConfigJsonMarshaller() {
    }

    public void marshall(PresignedUrlConfig presignedUrlConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (presignedUrlConfig.getRoleArn() != null) {
            String roleArn = presignedUrlConfig.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (presignedUrlConfig.getExpiresInSec() != null) {
            Long expiresInSec = presignedUrlConfig.getExpiresInSec();
            awsJsonWriter.name("expiresInSec");
            awsJsonWriter.value(expiresInSec);
        }
        awsJsonWriter.endObject();
    }

    public static PresignedUrlConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PresignedUrlConfigJsonMarshaller();
        }
        return instance;
    }
}
