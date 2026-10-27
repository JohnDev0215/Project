package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.ThingConnectivity;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ThingConnectivityJsonMarshaller {
    private static ThingConnectivityJsonMarshaller instance;

    ThingConnectivityJsonMarshaller() {
    }

    public void marshall(ThingConnectivity thingConnectivity, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingConnectivity.getConnected() != null) {
            Boolean connected = thingConnectivity.getConnected();
            awsJsonWriter.name("connected");
            awsJsonWriter.value(connected.booleanValue());
        }
        if (thingConnectivity.getTimestamp() != null) {
            Long timestamp = thingConnectivity.getTimestamp();
            awsJsonWriter.name(TetheredMessageKey.timestamp);
            awsJsonWriter.value(timestamp);
        }
        if (thingConnectivity.getDisconnectReason() != null) {
            String disconnectReason = thingConnectivity.getDisconnectReason();
            awsJsonWriter.name("disconnectReason");
            awsJsonWriter.value(disconnectReason);
        }
        awsJsonWriter.endObject();
    }

    public static ThingConnectivityJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingConnectivityJsonMarshaller();
        }
        return instance;
    }
}
