package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeDefinition;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingTypeDefinitionJsonUnmarshaller implements Unmarshaller<ThingTypeDefinition, JsonUnmarshallerContext> {
    private static ThingTypeDefinitionJsonUnmarshaller instance;

    ThingTypeDefinitionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingTypeDefinition unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingTypeDefinition thingTypeDefinition = new ThingTypeDefinition();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingTypeName")) {
                thingTypeDefinition.setThingTypeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeArn")) {
                thingTypeDefinition.setThingTypeArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeProperties")) {
                thingTypeDefinition.setThingTypeProperties(ThingTypePropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeMetadata")) {
                thingTypeDefinition.setThingTypeMetadata(ThingTypeMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingTypeDefinition;
    }

    public static ThingTypeDefinitionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeDefinitionJsonUnmarshaller();
        }
        return instance;
    }
}
