package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.services.iot.model.UpdateDimensionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDimensionResultJsonUnmarshaller implements Unmarshaller<UpdateDimensionResult, JsonUnmarshallerContext> {
    private static UpdateDimensionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateDimensionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateDimensionResult updateDimensionResult = new UpdateDimensionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                updateDimensionResult.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("arn")) {
                updateDimensionResult.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                updateDimensionResult.setType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stringValues")) {
                updateDimensionResult.setStringValues(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                updateDimensionResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                updateDimensionResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateDimensionResult;
    }

    public static UpdateDimensionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateDimensionResultJsonUnmarshaller();
        }
        return instance;
    }
}
