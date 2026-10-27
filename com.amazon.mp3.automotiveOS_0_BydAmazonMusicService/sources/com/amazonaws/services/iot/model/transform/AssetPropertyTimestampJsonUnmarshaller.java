package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssetPropertyTimestamp;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyTimestampJsonUnmarshaller implements Unmarshaller<AssetPropertyTimestamp, JsonUnmarshallerContext> {
    private static AssetPropertyTimestampJsonUnmarshaller instance;

    AssetPropertyTimestampJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AssetPropertyTimestamp unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AssetPropertyTimestamp assetPropertyTimestamp = new AssetPropertyTimestamp();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("timeInSeconds")) {
                assetPropertyTimestamp.setTimeInSeconds(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("offsetInNanos")) {
                assetPropertyTimestamp.setOffsetInNanos(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return assetPropertyTimestamp;
    }

    public static AssetPropertyTimestampJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyTimestampJsonUnmarshaller();
        }
        return instance;
    }
}
