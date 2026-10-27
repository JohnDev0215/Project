package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateMitigationActionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateMitigationActionResultJsonUnmarshaller implements Unmarshaller<UpdateMitigationActionResult, JsonUnmarshallerContext> {
    private static UpdateMitigationActionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateMitigationActionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateMitigationActionResult updateMitigationActionResult = new UpdateMitigationActionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionArn")) {
                updateMitigationActionResult.setActionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionId")) {
                updateMitigationActionResult.setActionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateMitigationActionResult;
    }

    public static UpdateMitigationActionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateMitigationActionResultJsonUnmarshaller();
        }
        return instance;
    }
}
