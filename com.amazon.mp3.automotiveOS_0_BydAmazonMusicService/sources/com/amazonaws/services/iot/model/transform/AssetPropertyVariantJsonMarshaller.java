package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssetPropertyVariant;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyVariantJsonMarshaller {
    private static AssetPropertyVariantJsonMarshaller instance;

    AssetPropertyVariantJsonMarshaller() {
    }

    public void marshall(AssetPropertyVariant assetPropertyVariant, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (assetPropertyVariant.getStringValue() != null) {
            String stringValue = assetPropertyVariant.getStringValue();
            awsJsonWriter.name("stringValue");
            awsJsonWriter.value(stringValue);
        }
        if (assetPropertyVariant.getIntegerValue() != null) {
            String integerValue = assetPropertyVariant.getIntegerValue();
            awsJsonWriter.name("integerValue");
            awsJsonWriter.value(integerValue);
        }
        if (assetPropertyVariant.getDoubleValue() != null) {
            String doubleValue = assetPropertyVariant.getDoubleValue();
            awsJsonWriter.name("doubleValue");
            awsJsonWriter.value(doubleValue);
        }
        if (assetPropertyVariant.getBooleanValue() != null) {
            String booleanValue = assetPropertyVariant.getBooleanValue();
            awsJsonWriter.name("booleanValue");
            awsJsonWriter.value(booleanValue);
        }
        awsJsonWriter.endObject();
    }

    public static AssetPropertyVariantJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyVariantJsonMarshaller();
        }
        return instance;
    }
}
