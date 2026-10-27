package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuditMitigationActionsTasksResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsTasksResultJsonUnmarshaller implements Unmarshaller<ListAuditMitigationActionsTasksResult, JsonUnmarshallerContext> {
    private static ListAuditMitigationActionsTasksResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuditMitigationActionsTasksResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuditMitigationActionsTasksResult listAuditMitigationActionsTasksResult = new ListAuditMitigationActionsTasksResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("tasks")) {
                listAuditMitigationActionsTasksResult.setTasks(new ListUnmarshaller(AuditMitigationActionsTaskMetadataJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listAuditMitigationActionsTasksResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuditMitigationActionsTasksResult;
    }

    public static ListAuditMitigationActionsTasksResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuditMitigationActionsTasksResultJsonUnmarshaller();
        }
        return instance;
    }
}
