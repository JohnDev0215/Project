package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssetPropertyTimestamp;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AssetPropertyTimestampJsonMarshaller {
    private static AssetPropertyTimestampJsonMarshaller instance;

    AssetPropertyTimestampJsonMarshaller() {
    }

    public void marshall(AssetPropertyTimestamp assetPropertyTimestamp, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (assetPropertyTimestamp.getTimeInSeconds() != null) {
            String timeInSeconds = assetPropertyTimestamp.getTimeInSeconds();
            awsJsonWriter.name("timeInSeconds");
            awsJsonWriter.value(timeInSeconds);
        }
        if (assetPropertyTimestamp.getOffsetInNanos() != null) {
            String offsetInNanos = assetPropertyTimestamp.getOffsetInNanos();
            awsJsonWriter.name("offsetInNanos");
            awsJsonWriter.value(offsetInNanos);
        }
        awsJsonWriter.endObject();
    }

    public static AssetPropertyTimestampJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AssetPropertyTimestampJsonMarshaller();
        }
        return instance;
    }
}
