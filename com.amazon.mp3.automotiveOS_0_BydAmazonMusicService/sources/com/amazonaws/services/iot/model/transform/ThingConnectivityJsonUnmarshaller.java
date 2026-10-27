package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.ThingConnectivity;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ThingConnectivityJsonUnmarshaller implements Unmarshaller<ThingConnectivity, JsonUnmarshallerContext> {
    private static ThingConnectivityJsonUnmarshaller instance;

    ThingConnectivityJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingConnectivity unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingConnectivity thingConnectivity = new ThingConnectivity();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("connected")) {
                thingConnectivity.setConnected(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(TetheredMessageKey.timestamp)) {
                thingConnectivity.setTimestamp(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("disconnectReason")) {
                thingConnectivity.setDisconnectReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingConnectivity;
    }

    public static ThingConnectivityJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingConnectivityJsonUnmarshaller();
        }
        return instance;
    }
}
