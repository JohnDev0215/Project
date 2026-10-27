package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Location;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class S3LocationJsonUnmarshaller implements Unmarshaller<S3Location, JsonUnmarshallerContext> {
    private static S3LocationJsonUnmarshaller instance;

    S3LocationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public S3Location unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        S3Location s3Location = new S3Location();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("bucket")) {
                s3Location.setBucket(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("key")) {
                s3Location.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                s3Location.setVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return s3Location;
    }

    public static S3LocationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new S3LocationJsonUnmarshaller();
        }
        return instance;
    }
}
