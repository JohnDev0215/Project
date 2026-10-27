package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Destination;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class S3DestinationJsonUnmarshaller implements Unmarshaller<S3Destination, JsonUnmarshallerContext> {
    private static S3DestinationJsonUnmarshaller instance;

    S3DestinationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public S3Destination unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        S3Destination s3Destination = new S3Destination();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("bucket")) {
                s3Destination.setBucket(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("prefix")) {
                s3Destination.setPrefix(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return s3Destination;
    }

    public static S3DestinationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new S3DestinationJsonUnmarshaller();
        }
        return instance;
    }
}
