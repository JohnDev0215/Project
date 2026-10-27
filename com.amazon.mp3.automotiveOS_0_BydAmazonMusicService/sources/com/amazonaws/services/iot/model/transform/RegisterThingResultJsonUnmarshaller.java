package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegisterThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterThingResultJsonUnmarshaller implements Unmarshaller<RegisterThingResult, JsonUnmarshallerContext> {
    private static RegisterThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public RegisterThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        RegisterThingResult registerThingResult = new RegisterThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificatePem")) {
                registerThingResult.setCertificatePem(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("resourceArns")) {
                registerThingResult.setResourceArns(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return registerThingResult;
    }

    public static RegisterThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RegisterThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
