package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PutAssetPropertyValueEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PutAssetPropertyValueEntryJsonUnmarshaller implements Unmarshaller<PutAssetPropertyValueEntry, JsonUnmarshallerContext> {
    private static PutAssetPropertyValueEntryJsonUnmarshaller instance;

    PutAssetPropertyValueEntryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutAssetPropertyValueEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PutAssetPropertyValueEntry putAssetPropertyValueEntry = new PutAssetPropertyValueEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("entryId")) {
                putAssetPropertyValueEntry.setEntryId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("assetId")) {
                putAssetPropertyValueEntry.setAssetId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("propertyId")) {
                putAssetPropertyValueEntry.setPropertyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("propertyAlias")) {
                putAssetPropertyValueEntry.setPropertyAlias(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("propertyValues")) {
                putAssetPropertyValueEntry.setPropertyValues(new ListUnmarshaller(AssetPropertyValueJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putAssetPropertyValueEntry;
    }

    public static PutAssetPropertyValueEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutAssetPropertyValueEntryJsonUnmarshaller();
        }
        return instance;
    }
}
