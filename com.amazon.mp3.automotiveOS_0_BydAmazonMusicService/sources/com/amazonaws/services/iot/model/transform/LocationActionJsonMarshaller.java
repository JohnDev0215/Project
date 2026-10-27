package com.amazonaws.services.iot.model.transform;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.LocationAction;
import com.amazonaws.services.iot.model.LocationTimestamp;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class LocationActionJsonMarshaller {
    private static LocationActionJsonMarshaller instance;

    LocationActionJsonMarshaller() {
    }

    public void marshall(LocationAction locationAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (locationAction.getRoleArn() != null) {
            String roleArn = locationAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (locationAction.getTrackerName() != null) {
            String trackerName = locationAction.getTrackerName();
            awsJsonWriter.name("trackerName");
            awsJsonWriter.value(trackerName);
        }
        if (locationAction.getDeviceId() != null) {
            String deviceId = locationAction.getDeviceId();
            awsJsonWriter.name(Splash.PARAMS_DEVICE_ID);
            awsJsonWriter.value(deviceId);
        }
        if (locationAction.getTimestamp() != null) {
            LocationTimestamp timestamp = locationAction.getTimestamp();
            awsJsonWriter.name(TetheredMessageKey.timestamp);
            LocationTimestampJsonMarshaller.getInstance().marshall(timestamp, awsJsonWriter);
        }
        if (locationAction.getLatitude() != null) {
            String latitude = locationAction.getLatitude();
            awsJsonWriter.name("latitude");
            awsJsonWriter.value(latitude);
        }
        if (locationAction.getLongitude() != null) {
            String longitude = locationAction.getLongitude();
            awsJsonWriter.name("longitude");
            awsJsonWriter.value(longitude);
        }
        awsJsonWriter.endObject();
    }

    public static LocationActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LocationActionJsonMarshaller();
        }
        return instance;
    }
}
