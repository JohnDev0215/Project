package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SnsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SnsActionJsonUnmarshaller implements Unmarshaller<SnsAction, JsonUnmarshallerContext> {
    private static SnsActionJsonUnmarshaller instance;

    SnsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SnsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SnsAction snsAction = new SnsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("targetArn")) {
                snsAction.setTargetArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                snsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("messageFormat")) {
                snsAction.setMessageFormat(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return snsAction;
    }

    public static SnsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SnsActionJsonUnmarshaller();
        }
        return instance;
    }
}
