package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobPresignedUrlConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobPresignedUrlConfigJsonUnmarshaller implements Unmarshaller<AwsJobPresignedUrlConfig, JsonUnmarshallerContext> {
    private static AwsJobPresignedUrlConfigJsonUnmarshaller instance;

    AwsJobPresignedUrlConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobPresignedUrlConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobPresignedUrlConfig awsJobPresignedUrlConfig = new AwsJobPresignedUrlConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("expiresInSec")) {
                awsJobPresignedUrlConfig.setExpiresInSec(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobPresignedUrlConfig;
    }

    public static AwsJobPresignedUrlConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobPresignedUrlConfigJsonUnmarshaller();
        }
        return instance;
    }
}
