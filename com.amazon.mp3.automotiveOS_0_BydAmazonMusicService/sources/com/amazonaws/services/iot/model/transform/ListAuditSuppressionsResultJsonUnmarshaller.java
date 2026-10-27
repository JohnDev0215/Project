package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuditSuppressionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditSuppressionsResultJsonUnmarshaller implements Unmarshaller<ListAuditSuppressionsResult, JsonUnmarshallerContext> {
    private static ListAuditSuppressionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuditSuppressionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuditSuppressionsResult listAuditSuppressionsResult = new ListAuditSuppressionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("suppressions")) {
                listAuditSuppressionsResult.setSuppressions(new ListUnmarshaller(AuditSuppressionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listAuditSuppressionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuditSuppressionsResult;
    }

    public static ListAuditSuppressionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuditSuppressionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
