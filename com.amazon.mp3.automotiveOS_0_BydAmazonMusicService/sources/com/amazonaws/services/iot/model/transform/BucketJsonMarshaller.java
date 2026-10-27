package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Bucket;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class BucketJsonMarshaller {
    private static BucketJsonMarshaller instance;

    BucketJsonMarshaller() {
    }

    public void marshall(Bucket bucket, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (bucket.getKeyValue() != null) {
            String keyValue = bucket.getKeyValue();
            awsJsonWriter.name("keyValue");
            awsJsonWriter.value(keyValue);
        }
        if (bucket.getCount() != null) {
            Integer count = bucket.getCount();
            awsJsonWriter.name("count");
            awsJsonWriter.value(count);
        }
        awsJsonWriter.endObject();
    }

    public static BucketJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BucketJsonMarshaller();
        }
        return instance;
    }
}
