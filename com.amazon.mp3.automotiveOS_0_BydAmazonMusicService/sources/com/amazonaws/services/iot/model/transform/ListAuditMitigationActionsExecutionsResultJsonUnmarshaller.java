package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuditMitigationActionsExecutionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsExecutionsResultJsonUnmarshaller implements Unmarshaller<ListAuditMitigationActionsExecutionsResult, JsonUnmarshallerContext> {
    private static ListAuditMitigationActionsExecutionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuditMitigationActionsExecutionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuditMitigationActionsExecutionsResult listAuditMitigationActionsExecutionsResult = new ListAuditMitigationActionsExecutionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionsExecutions")) {
                listAuditMitigationActionsExecutionsResult.setActionsExecutions(new ListUnmarshaller(AuditMitigationActionExecutionMetadataJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listAuditMitigationActionsExecutionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuditMitigationActionsExecutionsResult;
    }

    public static ListAuditMitigationActionsExecutionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuditMitigationActionsExecutionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
