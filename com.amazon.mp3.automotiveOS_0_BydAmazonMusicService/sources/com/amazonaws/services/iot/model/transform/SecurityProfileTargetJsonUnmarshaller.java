package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SecurityProfileTargetJsonUnmarshaller implements Unmarshaller<SecurityProfileTarget, JsonUnmarshallerContext> {
    private static SecurityProfileTargetJsonUnmarshaller instance;

    SecurityProfileTargetJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SecurityProfileTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SecurityProfileTarget securityProfileTarget = new SecurityProfileTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("arn")) {
                securityProfileTarget.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return securityProfileTarget;
    }

    public static SecurityProfileTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileTargetJsonUnmarshaller();
        }
        return instance;
    }
}
