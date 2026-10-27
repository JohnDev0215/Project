package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingGroupsForThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsForThingResultJsonUnmarshaller implements Unmarshaller<ListThingGroupsForThingResult, JsonUnmarshallerContext> {
    private static ListThingGroupsForThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingGroupsForThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingGroupsForThingResult listThingGroupsForThingResult = new ListThingGroupsForThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroups")) {
                listThingGroupsForThingResult.setThingGroups(new ListUnmarshaller(GroupNameAndArnJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingGroupsForThingResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingGroupsForThingResult;
    }

    public static ListThingGroupsForThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingGroupsForThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
