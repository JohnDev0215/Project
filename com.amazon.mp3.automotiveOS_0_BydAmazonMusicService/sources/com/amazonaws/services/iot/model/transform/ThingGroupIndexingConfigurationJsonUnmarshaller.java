package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupIndexingConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingGroupIndexingConfigurationJsonUnmarshaller implements Unmarshaller<ThingGroupIndexingConfiguration, JsonUnmarshallerContext> {
    private static ThingGroupIndexingConfigurationJsonUnmarshaller instance;

    ThingGroupIndexingConfigurationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingGroupIndexingConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingGroupIndexingConfiguration thingGroupIndexingConfiguration = new ThingGroupIndexingConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroupIndexingMode")) {
                thingGroupIndexingConfiguration.setThingGroupIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("managedFields")) {
                thingGroupIndexingConfiguration.setManagedFields(new ListUnmarshaller(FieldJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("customFields")) {
                thingGroupIndexingConfiguration.setCustomFields(new ListUnmarshaller(FieldJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingGroupIndexingConfiguration;
    }

    public static ThingGroupIndexingConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupIndexingConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
