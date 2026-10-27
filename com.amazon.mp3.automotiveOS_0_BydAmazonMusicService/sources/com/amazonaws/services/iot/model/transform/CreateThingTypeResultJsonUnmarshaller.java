package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateThingTypeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingTypeResultJsonUnmarshaller implements Unmarshaller<CreateThingTypeResult, JsonUnmarshallerContext> {
    private static CreateThingTypeResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateThingTypeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateThingTypeResult createThingTypeResult = new CreateThingTypeResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingTypeName")) {
                createThingTypeResult.setThingTypeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeArn")) {
                createThingTypeResult.setThingTypeArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeId")) {
                createThingTypeResult.setThingTypeId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createThingTypeResult;
    }

    public static CreateThingTypeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateThingTypeResultJsonUnmarshaller();
        }
        return instance;
    }
}
