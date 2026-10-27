package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingGroupsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsResultJsonUnmarshaller implements Unmarshaller<ListThingGroupsResult, JsonUnmarshallerContext> {
    private static ListThingGroupsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingGroupsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingGroupsResult listThingGroupsResult = new ListThingGroupsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroups")) {
                listThingGroupsResult.setThingGroups(new ListUnmarshaller(GroupNameAndArnJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingGroupsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingGroupsResult;
    }

    public static ListThingGroupsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingGroupsResultJsonUnmarshaller();
        }
        return instance;
    }
}
