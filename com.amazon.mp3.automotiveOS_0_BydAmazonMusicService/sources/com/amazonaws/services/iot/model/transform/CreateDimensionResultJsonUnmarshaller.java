package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateDimensionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDimensionResultJsonUnmarshaller implements Unmarshaller<CreateDimensionResult, JsonUnmarshallerContext> {
    private static CreateDimensionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateDimensionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateDimensionResult createDimensionResult = new CreateDimensionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                createDimensionResult.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("arn")) {
                createDimensionResult.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createDimensionResult;
    }

    public static CreateDimensionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateDimensionResultJsonUnmarshaller();
        }
        return instance;
    }
}
