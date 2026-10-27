package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingsInThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsInThingGroupResultJsonUnmarshaller implements Unmarshaller<ListThingsInThingGroupResult, JsonUnmarshallerContext> {
    private static ListThingsInThingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingsInThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingsInThingGroupResult listThingsInThingGroupResult = new ListThingsInThingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("things")) {
                listThingsInThingGroupResult.setThings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingsInThingGroupResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingsInThingGroupResult;
    }

    public static ListThingsInThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingsInThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
