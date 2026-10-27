package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotAnalyticsAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class IotAnalyticsActionJsonMarshaller {
    private static IotAnalyticsActionJsonMarshaller instance;

    IotAnalyticsActionJsonMarshaller() {
    }

    public void marshall(IotAnalyticsAction iotAnalyticsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (iotAnalyticsAction.getChannelArn() != null) {
            String channelArn = iotAnalyticsAction.getChannelArn();
            awsJsonWriter.name("channelArn");
            awsJsonWriter.value(channelArn);
        }
        if (iotAnalyticsAction.getChannelName() != null) {
            String channelName = iotAnalyticsAction.getChannelName();
            awsJsonWriter.name("channelName");
            awsJsonWriter.value(channelName);
        }
        if (iotAnalyticsAction.getBatchMode() != null) {
            Boolean batchMode = iotAnalyticsAction.getBatchMode();
            awsJsonWriter.name("batchMode");
            awsJsonWriter.value(batchMode.booleanValue());
        }
        if (iotAnalyticsAction.getRoleArn() != null) {
            String roleArn = iotAnalyticsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static IotAnalyticsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IotAnalyticsActionJsonMarshaller();
        }
        return instance;
    }
}
