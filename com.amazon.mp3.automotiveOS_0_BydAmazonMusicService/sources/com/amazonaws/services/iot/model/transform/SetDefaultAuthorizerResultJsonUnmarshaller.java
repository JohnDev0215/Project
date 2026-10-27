package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SetDefaultAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class SetDefaultAuthorizerResultJsonUnmarshaller implements Unmarshaller<SetDefaultAuthorizerResult, JsonUnmarshallerContext> {
    private static SetDefaultAuthorizerResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public SetDefaultAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        SetDefaultAuthorizerResult setDefaultAuthorizerResult = new SetDefaultAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("authorizerName")) {
                setDefaultAuthorizerResult.setAuthorizerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("authorizerArn")) {
                setDefaultAuthorizerResult.setAuthorizerArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return setDefaultAuthorizerResult;
    }

    public static SetDefaultAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SetDefaultAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }
}
