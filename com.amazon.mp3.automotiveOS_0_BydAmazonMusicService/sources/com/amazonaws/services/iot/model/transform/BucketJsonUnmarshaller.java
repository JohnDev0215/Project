package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Bucket;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BucketJsonUnmarshaller implements Unmarshaller<Bucket, JsonUnmarshallerContext> {
    private static BucketJsonUnmarshaller instance;

    BucketJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Bucket unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Bucket bucket = new Bucket();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("keyValue")) {
                bucket.setKeyValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("count")) {
                bucket.setCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return bucket;
    }

    public static BucketJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BucketJsonUnmarshaller();
        }
        return instance;
    }
}
