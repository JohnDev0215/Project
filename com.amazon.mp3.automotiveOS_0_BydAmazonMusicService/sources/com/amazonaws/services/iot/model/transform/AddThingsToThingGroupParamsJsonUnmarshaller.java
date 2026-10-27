package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AddThingsToThingGroupParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AddThingsToThingGroupParamsJsonUnmarshaller implements Unmarshaller<AddThingsToThingGroupParams, JsonUnmarshallerContext> {
    private static AddThingsToThingGroupParamsJsonUnmarshaller instance;

    AddThingsToThingGroupParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AddThingsToThingGroupParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AddThingsToThingGroupParams addThingsToThingGroupParams = new AddThingsToThingGroupParams();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroupNames")) {
                addThingsToThingGroupParams.setThingGroupNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("overrideDynamicGroups")) {
                addThingsToThingGroupParams.setOverrideDynamicGroups(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return addThingsToThingGroupParams;
    }

    public static AddThingsToThingGroupParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AddThingsToThingGroupParamsJsonUnmarshaller();
        }
        return instance;
    }
}
