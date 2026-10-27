package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingResultJsonUnmarshaller implements Unmarshaller<CreateThingResult, JsonUnmarshallerContext> {
    private static CreateThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateThingResult createThingResult = new CreateThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingName")) {
                createThingResult.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingArn")) {
                createThingResult.setThingArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingId")) {
                createThingResult.setThingId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createThingResult;
    }

    public static CreateThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
