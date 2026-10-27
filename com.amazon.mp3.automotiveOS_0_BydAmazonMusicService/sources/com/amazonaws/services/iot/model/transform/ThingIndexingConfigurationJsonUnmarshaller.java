package com.amazonaws.services.iot.model.transform;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazonaws.services.iot.model.ThingIndexingConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingIndexingConfigurationJsonUnmarshaller implements Unmarshaller<ThingIndexingConfiguration, JsonUnmarshallerContext> {
    private static ThingIndexingConfigurationJsonUnmarshaller instance;

    ThingIndexingConfigurationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingIndexingConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingIndexingConfiguration thingIndexingConfiguration = new ThingIndexingConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingIndexingMode")) {
                thingIndexingConfiguration.setThingIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingConnectivityIndexingMode")) {
                thingIndexingConfiguration.setThingConnectivityIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("deviceDefenderIndexingMode")) {
                thingIndexingConfiguration.setDeviceDefenderIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("namedShadowIndexingMode")) {
                thingIndexingConfiguration.setNamedShadowIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("managedFields")) {
                thingIndexingConfiguration.setManagedFields(new ListUnmarshaller(FieldJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("customFields")) {
                thingIndexingConfiguration.setCustomFields(new ListUnmarshaller(FieldJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(PageUriUtils.FILTER_QUERY_PARAM)) {
                thingIndexingConfiguration.setFilter(IndexingFilterJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingIndexingConfiguration;
    }

    public static ThingIndexingConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingIndexingConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
