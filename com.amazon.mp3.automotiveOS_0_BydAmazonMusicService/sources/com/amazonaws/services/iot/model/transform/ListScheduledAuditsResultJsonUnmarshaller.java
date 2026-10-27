package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListScheduledAuditsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListScheduledAuditsResultJsonUnmarshaller implements Unmarshaller<ListScheduledAuditsResult, JsonUnmarshallerContext> {
    private static ListScheduledAuditsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListScheduledAuditsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListScheduledAuditsResult listScheduledAuditsResult = new ListScheduledAuditsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("scheduledAudits")) {
                listScheduledAuditsResult.setScheduledAudits(new ListUnmarshaller(ScheduledAuditMetadataJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listScheduledAuditsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listScheduledAuditsResult;
    }

    public static ListScheduledAuditsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListScheduledAuditsResultJsonUnmarshaller();
        }
        return instance;
    }
}
