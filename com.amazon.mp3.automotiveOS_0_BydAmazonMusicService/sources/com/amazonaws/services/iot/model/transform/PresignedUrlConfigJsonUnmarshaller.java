package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PresignedUrlConfigJsonUnmarshaller implements Unmarshaller<PresignedUrlConfig, JsonUnmarshallerContext> {
    private static PresignedUrlConfigJsonUnmarshaller instance;

    PresignedUrlConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PresignedUrlConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PresignedUrlConfig presignedUrlConfig = new PresignedUrlConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                presignedUrlConfig.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("expiresInSec")) {
                presignedUrlConfig.setExpiresInSec(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return presignedUrlConfig;
    }

    public static PresignedUrlConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PresignedUrlConfigJsonUnmarshaller();
        }
        return instance;
    }
}
