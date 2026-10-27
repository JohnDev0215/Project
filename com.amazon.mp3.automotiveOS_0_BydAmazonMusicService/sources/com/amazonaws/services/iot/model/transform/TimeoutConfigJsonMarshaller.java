package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TimeoutConfigJsonMarshaller {
    private static TimeoutConfigJsonMarshaller instance;

    TimeoutConfigJsonMarshaller() {
    }

    public void marshall(TimeoutConfig timeoutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (timeoutConfig.getInProgressTimeoutInMinutes() != null) {
            Long inProgressTimeoutInMinutes = timeoutConfig.getInProgressTimeoutInMinutes();
            awsJsonWriter.name("inProgressTimeoutInMinutes");
            awsJsonWriter.value(inProgressTimeoutInMinutes);
        }
        awsJsonWriter.endObject();
    }

    public static TimeoutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TimeoutConfigJsonMarshaller();
        }
        return instance;
    }
}
