package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListPrincipalThingsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListPrincipalThingsResultJsonUnmarshaller implements Unmarshaller<ListPrincipalThingsResult, JsonUnmarshallerContext> {
    private static ListPrincipalThingsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPrincipalThingsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPrincipalThingsResult listPrincipalThingsResult = new ListPrincipalThingsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("things")) {
                listPrincipalThingsResult.setThings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listPrincipalThingsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPrincipalThingsResult;
    }

    public static ListPrincipalThingsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPrincipalThingsResultJsonUnmarshaller();
        }
        return instance;
    }
}
