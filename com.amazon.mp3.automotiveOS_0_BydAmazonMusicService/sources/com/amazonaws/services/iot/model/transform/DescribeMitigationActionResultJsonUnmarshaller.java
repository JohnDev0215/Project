package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeMitigationActionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeMitigationActionResultJsonUnmarshaller implements Unmarshaller<DescribeMitigationActionResult, JsonUnmarshallerContext> {
    private static DescribeMitigationActionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeMitigationActionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeMitigationActionResult describeMitigationActionResult = new DescribeMitigationActionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionName")) {
                describeMitigationActionResult.setActionName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionType")) {
                describeMitigationActionResult.setActionType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionArn")) {
                describeMitigationActionResult.setActionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionId")) {
                describeMitigationActionResult.setActionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                describeMitigationActionResult.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionParams")) {
                describeMitigationActionResult.setActionParams(MitigationActionParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                describeMitigationActionResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                describeMitigationActionResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeMitigationActionResult;
    }

    public static DescribeMitigationActionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeMitigationActionResultJsonUnmarshaller();
        }
        return instance;
    }
}
