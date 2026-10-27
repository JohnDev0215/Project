package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateMitigationActionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateMitigationActionResultJsonUnmarshaller implements Unmarshaller<CreateMitigationActionResult, JsonUnmarshallerContext> {
    private static CreateMitigationActionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateMitigationActionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateMitigationActionResult createMitigationActionResult = new CreateMitigationActionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionArn")) {
                createMitigationActionResult.setActionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionId")) {
                createMitigationActionResult.setActionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createMitigationActionResult;
    }

    public static CreateMitigationActionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateMitigationActionResultJsonUnmarshaller();
        }
        return instance;
    }
}
