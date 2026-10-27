package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupDocument;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingGroupDocumentJsonUnmarshaller implements Unmarshaller<ThingGroupDocument, JsonUnmarshallerContext> {
    private static ThingGroupDocumentJsonUnmarshaller instance;

    ThingGroupDocumentJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingGroupDocument unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingGroupDocument thingGroupDocument = new ThingGroupDocument();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroupName")) {
                thingGroupDocument.setThingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupId")) {
                thingGroupDocument.setThingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupDescription")) {
                thingGroupDocument.setThingGroupDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("attributes")) {
                thingGroupDocument.setAttributes(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("parentGroupNames")) {
                thingGroupDocument.setParentGroupNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingGroupDocument;
    }

    public static ThingGroupDocumentJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupDocumentJsonUnmarshaller();
        }
        return instance;
    }
}
