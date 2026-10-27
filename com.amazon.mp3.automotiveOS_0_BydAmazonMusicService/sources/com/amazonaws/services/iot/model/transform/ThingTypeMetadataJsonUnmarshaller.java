package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingTypeMetadataJsonUnmarshaller implements Unmarshaller<ThingTypeMetadata, JsonUnmarshallerContext> {
    private static ThingTypeMetadataJsonUnmarshaller instance;

    ThingTypeMetadataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingTypeMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingTypeMetadata thingTypeMetadata = new ThingTypeMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("deprecated")) {
                thingTypeMetadata.setDeprecated(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("deprecationDate")) {
                thingTypeMetadata.setDeprecationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                thingTypeMetadata.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingTypeMetadata;
    }

    public static ThingTypeMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeMetadataJsonUnmarshaller();
        }
        return instance;
    }
}
