package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Location;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class S3LocationJsonMarshaller {
    private static S3LocationJsonMarshaller instance;

    S3LocationJsonMarshaller() {
    }

    public void marshall(S3Location s3Location, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (s3Location.getBucket() != null) {
            String bucket = s3Location.getBucket();
            awsJsonWriter.name("bucket");
            awsJsonWriter.value(bucket);
        }
        if (s3Location.getKey() != null) {
            String key = s3Location.getKey();
            awsJsonWriter.name("key");
            awsJsonWriter.value(key);
        }
        if (s3Location.getVersion() != null) {
            String version = s3Location.getVersion();
            awsJsonWriter.name("version");
            awsJsonWriter.value(version);
        }
        awsJsonWriter.endObject();
    }

    public static S3LocationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new S3LocationJsonMarshaller();
        }
        return instance;
    }
}
