package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListDetectMitigationActionsExecutionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsExecutionsResultJsonUnmarshaller implements Unmarshaller<ListDetectMitigationActionsExecutionsResult, JsonUnmarshallerContext> {
    private static ListDetectMitigationActionsExecutionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListDetectMitigationActionsExecutionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListDetectMitigationActionsExecutionsResult listDetectMitigationActionsExecutionsResult = new ListDetectMitigationActionsExecutionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionsExecutions")) {
                listDetectMitigationActionsExecutionsResult.setActionsExecutions(new ListUnmarshaller(DetectMitigationActionExecutionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listDetectMitigationActionsExecutionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listDetectMitigationActionsExecutionsResult;
    }

    public static ListDetectMitigationActionsExecutionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListDetectMitigationActionsExecutionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
