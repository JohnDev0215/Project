package com.amazonaws.services.iot.model.transform;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.LocationAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class LocationActionJsonUnmarshaller implements Unmarshaller<LocationAction, JsonUnmarshallerContext> {
    private static LocationActionJsonUnmarshaller instance;

    LocationActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LocationAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LocationAction locationAction = new LocationAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                locationAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("trackerName")) {
                locationAction.setTrackerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(Splash.PARAMS_DEVICE_ID)) {
                locationAction.setDeviceId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(TetheredMessageKey.timestamp)) {
                locationAction.setTimestamp(LocationTimestampJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("latitude")) {
                locationAction.setLatitude(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("longitude")) {
                locationAction.setLongitude(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return locationAction;
    }

    public static LocationActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LocationActionJsonUnmarshaller();
        }
        return instance;
    }
}
