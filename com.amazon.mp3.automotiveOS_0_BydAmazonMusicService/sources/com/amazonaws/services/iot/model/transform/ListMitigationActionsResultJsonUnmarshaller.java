package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListMitigationActionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListMitigationActionsResultJsonUnmarshaller implements Unmarshaller<ListMitigationActionsResult, JsonUnmarshallerContext> {
    private static ListMitigationActionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListMitigationActionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListMitigationActionsResult listMitigationActionsResult = new ListMitigationActionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionIdentifiers")) {
                listMitigationActionsResult.setActionIdentifiers(new ListUnmarshaller(MitigationActionIdentifierJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listMitigationActionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listMitigationActionsResult;
    }

    public static ListMitigationActionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListMitigationActionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
