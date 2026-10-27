package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobPresignedUrlConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobPresignedUrlConfigJsonMarshaller {
    private static AwsJobPresignedUrlConfigJsonMarshaller instance;

    AwsJobPresignedUrlConfigJsonMarshaller() {
    }

    public void marshall(AwsJobPresignedUrlConfig awsJobPresignedUrlConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobPresignedUrlConfig.getExpiresInSec() != null) {
            Long expiresInSec = awsJobPresignedUrlConfig.getExpiresInSec();
            awsJsonWriter.name("expiresInSec");
            awsJsonWriter.value(expiresInSec);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobPresignedUrlConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobPresignedUrlConfigJsonMarshaller();
        }
        return instance;
    }
}
