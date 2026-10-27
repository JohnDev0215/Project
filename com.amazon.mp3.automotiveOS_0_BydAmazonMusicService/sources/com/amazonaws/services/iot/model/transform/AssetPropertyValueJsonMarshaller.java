package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.AssetPropertyTimestamp;
import com.amazonaws.services.iot.model.AssetPropertyValue;
import com.amazonaws.services.iot.model.AssetPropertyVariant;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyValueJsonMarshaller {
    private static AssetPropertyValueJsonMarshaller instance;

    AssetPropertyValueJsonMarshaller() {
    }

    public void marshall(AssetPropertyValue assetPropertyValue, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (assetPropertyValue.getValue() != null) {
            AssetPropertyVariant value = assetPropertyValue.getValue();
            awsJsonWriter.name("value");
            AssetPropertyVariantJsonMarshaller.getInstance().marshall(value, awsJsonWriter);
        }
        if (assetPropertyValue.getTimestamp() != null) {
            AssetPropertyTimestamp timestamp = assetPropertyValue.getTimestamp();
            awsJsonWriter.name(TetheredMessageKey.timestamp);
            AssetPropertyTimestampJsonMarshaller.getInstance().marshall(timestamp, awsJsonWriter);
        }
        if (assetPropertyValue.getQuality() != null) {
            String quality = assetPropertyValue.getQuality();
            awsJsonWriter.name("quality");
            awsJsonWriter.value(quality);
        }
        awsJsonWriter.endObject();
    }

    public static AssetPropertyValueJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyValueJsonMarshaller();
        }
        return instance;
    }
}
