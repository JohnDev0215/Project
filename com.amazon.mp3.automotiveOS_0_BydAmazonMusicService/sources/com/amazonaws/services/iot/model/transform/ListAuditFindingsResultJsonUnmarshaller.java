package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuditFindingsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditFindingsResultJsonUnmarshaller implements Unmarshaller<ListAuditFindingsResult, JsonUnmarshallerContext> {
    private static ListAuditFindingsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuditFindingsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuditFindingsResult listAuditFindingsResult = new ListAuditFindingsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("findings")) {
                listAuditFindingsResult.setFindings(new ListUnmarshaller(AuditFindingJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listAuditFindingsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuditFindingsResult;
    }

    public static ListAuditFindingsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuditFindingsResultJsonUnmarshaller();
        }
        return instance;
    }
}
