package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListDetectMitigationActionsTasksResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsTasksResultJsonUnmarshaller implements Unmarshaller<ListDetectMitigationActionsTasksResult, JsonUnmarshallerContext> {
    private static ListDetectMitigationActionsTasksResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListDetectMitigationActionsTasksResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListDetectMitigationActionsTasksResult listDetectMitigationActionsTasksResult = new ListDetectMitigationActionsTasksResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("tasks")) {
                listDetectMitigationActionsTasksResult.setTasks(new ListUnmarshaller(DetectMitigationActionsTaskSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listDetectMitigationActionsTasksResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listDetectMitigationActionsTasksResult;
    }

    public static ListDetectMitigationActionsTasksResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListDetectMitigationActionsTasksResultJsonUnmarshaller();
        }
        return instance;
    }
}
