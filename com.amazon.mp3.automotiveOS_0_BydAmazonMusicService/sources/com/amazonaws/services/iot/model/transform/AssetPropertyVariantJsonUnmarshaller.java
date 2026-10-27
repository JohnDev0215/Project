package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssetPropertyVariant;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyVariantJsonUnmarshaller implements Unmarshaller<AssetPropertyVariant, JsonUnmarshallerContext> {
    private static AssetPropertyVariantJsonUnmarshaller instance;

    AssetPropertyVariantJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AssetPropertyVariant unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AssetPropertyVariant assetPropertyVariant = new AssetPropertyVariant();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("stringValue")) {
                assetPropertyVariant.setStringValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("integerValue")) {
                assetPropertyVariant.setIntegerValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("doubleValue")) {
                assetPropertyVariant.setDoubleValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("booleanValue")) {
                assetPropertyVariant.setBooleanValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return assetPropertyVariant;
    }

    public static AssetPropertyVariantJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyVariantJsonUnmarshaller();
        }
        return instance;
    }
}
