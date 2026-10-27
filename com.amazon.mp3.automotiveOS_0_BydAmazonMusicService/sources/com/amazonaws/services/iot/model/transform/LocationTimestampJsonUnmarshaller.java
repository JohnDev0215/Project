package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LocationTimestamp;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class LocationTimestampJsonUnmarshaller implements Unmarshaller<LocationTimestamp, JsonUnmarshallerContext> {
    private static LocationTimestampJsonUnmarshaller instance;

    LocationTimestampJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LocationTimestamp unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LocationTimestamp locationTimestamp = new LocationTimestamp();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("value")) {
                locationTimestamp.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("unit")) {
                locationTimestamp.setUnit(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return locationTimestamp;
    }

    public static LocationTimestampJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LocationTimestampJsonUnmarshaller();
        }
        return instance;
    }
}
