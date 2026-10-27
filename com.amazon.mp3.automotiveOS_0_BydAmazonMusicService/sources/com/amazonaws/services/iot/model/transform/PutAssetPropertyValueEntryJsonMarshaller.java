package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssetPropertyValue;
import com.amazonaws.services.iot.model.PutAssetPropertyValueEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class PutAssetPropertyValueEntryJsonMarshaller {
    private static PutAssetPropertyValueEntryJsonMarshaller instance;

    PutAssetPropertyValueEntryJsonMarshaller() {
    }

    public void marshall(PutAssetPropertyValueEntry putAssetPropertyValueEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (putAssetPropertyValueEntry.getEntryId() != null) {
            String entryId = putAssetPropertyValueEntry.getEntryId();
            awsJsonWriter.name("entryId");
            awsJsonWriter.value(entryId);
        }
        if (putAssetPropertyValueEntry.getAssetId() != null) {
            String assetId = putAssetPropertyValueEntry.getAssetId();
            awsJsonWriter.name("assetId");
            awsJsonWriter.value(assetId);
        }
        if (putAssetPropertyValueEntry.getPropertyId() != null) {
            String propertyId = putAssetPropertyValueEntry.getPropertyId();
            awsJsonWriter.name("propertyId");
            awsJsonWriter.value(propertyId);
        }
        if (putAssetPropertyValueEntry.getPropertyAlias() != null) {
            String propertyAlias = putAssetPropertyValueEntry.getPropertyAlias();
            awsJsonWriter.name("propertyAlias");
            awsJsonWriter.value(propertyAlias);
        }
        if (putAssetPropertyValueEntry.getPropertyValues() != null) {
            List<AssetPropertyValue> propertyValues = putAssetPropertyValueEntry.getPropertyValues();
            awsJsonWriter.name("propertyValues");
            awsJsonWriter.beginArray();
            for (AssetPropertyValue assetPropertyValue : propertyValues) {
                if (assetPropertyValue != null) {
                    AssetPropertyValueJsonMarshaller.getInstance().marshall(assetPropertyValue, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static PutAssetPropertyValueEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PutAssetPropertyValueEntryJsonMarshaller();
        }
        return instance;
    }
}
