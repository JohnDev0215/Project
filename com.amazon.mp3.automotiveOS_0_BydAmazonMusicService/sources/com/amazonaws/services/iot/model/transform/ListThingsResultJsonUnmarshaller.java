package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsResultJsonUnmarshaller implements Unmarshaller<ListThingsResult, JsonUnmarshallerContext> {
    private static ListThingsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingsResult listThingsResult = new ListThingsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("things")) {
                listThingsResult.setThings(new ListUnmarshaller(ThingAttributeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingsResult;
    }

    public static ListThingsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingsResultJsonUnmarshaller();
        }
        return instance;
    }
}
