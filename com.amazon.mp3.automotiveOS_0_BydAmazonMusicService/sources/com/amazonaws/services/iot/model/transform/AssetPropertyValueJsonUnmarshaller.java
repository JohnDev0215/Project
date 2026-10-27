package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.AssetPropertyValue;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyValueJsonUnmarshaller implements Unmarshaller<AssetPropertyValue, JsonUnmarshallerContext> {
    private static AssetPropertyValueJsonUnmarshaller instance;

    AssetPropertyValueJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AssetPropertyValue unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AssetPropertyValue assetPropertyValue = new AssetPropertyValue();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("value")) {
                assetPropertyValue.setValue(AssetPropertyVariantJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(TetheredMessageKey.timestamp)) {
                assetPropertyValue.setTimestamp(AssetPropertyTimestampJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("quality")) {
                assetPropertyValue.setQuality(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return assetPropertyValue;
    }

    public static AssetPropertyValueJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyValueJsonUnmarshaller();
        }
        return instance;
    }
}
