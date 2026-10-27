package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingGroupMetadataJsonUnmarshaller implements Unmarshaller<ThingGroupMetadata, JsonUnmarshallerContext> {
    private static ThingGroupMetadataJsonUnmarshaller instance;

    ThingGroupMetadataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingGroupMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingGroupMetadata thingGroupMetadata = new ThingGroupMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("parentGroupName")) {
                thingGroupMetadata.setParentGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rootToParentThingGroups")) {
                thingGroupMetadata.setRootToParentThingGroups(new ListUnmarshaller(GroupNameAndArnJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                thingGroupMetadata.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingGroupMetadata;
    }

    public static ThingGroupMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupMetadataJsonUnmarshaller();
        }
        return instance;
    }
}
