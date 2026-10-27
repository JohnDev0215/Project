package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateDynamicThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDynamicThingGroupResultJsonUnmarshaller implements Unmarshaller<CreateDynamicThingGroupResult, JsonUnmarshallerContext> {
    private static CreateDynamicThingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateDynamicThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateDynamicThingGroupResult createDynamicThingGroupResult = new CreateDynamicThingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroupName")) {
                createDynamicThingGroupResult.setThingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupArn")) {
                createDynamicThingGroupResult.setThingGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupId")) {
                createDynamicThingGroupResult.setThingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("indexName")) {
                createDynamicThingGroupResult.setIndexName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryString")) {
                createDynamicThingGroupResult.setQueryString(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryVersion")) {
                createDynamicThingGroupResult.setQueryVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createDynamicThingGroupResult;
    }

    public static CreateDynamicThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateDynamicThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
