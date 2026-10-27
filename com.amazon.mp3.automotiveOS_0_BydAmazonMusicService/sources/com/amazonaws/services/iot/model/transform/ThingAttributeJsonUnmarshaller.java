package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingAttribute;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingAttributeJsonUnmarshaller implements Unmarshaller<ThingAttribute, JsonUnmarshallerContext> {
    private static ThingAttributeJsonUnmarshaller instance;

    ThingAttributeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingAttribute unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingAttribute thingAttribute = new ThingAttribute();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingName")) {
                thingAttribute.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeName")) {
                thingAttribute.setThingTypeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingArn")) {
                thingAttribute.setThingArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("attributes")) {
                thingAttribute.setAttributes(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                thingAttribute.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingAttribute;
    }

    public static ThingAttributeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingAttributeJsonUnmarshaller();
        }
        return instance;
    }
}
