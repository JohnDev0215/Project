package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobTimeoutConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobTimeoutConfigJsonMarshaller {
    private static AwsJobTimeoutConfigJsonMarshaller instance;

    AwsJobTimeoutConfigJsonMarshaller() {
    }

    public void marshall(AwsJobTimeoutConfig awsJobTimeoutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobTimeoutConfig.getInProgressTimeoutInMinutes() != null) {
            Long inProgressTimeoutInMinutes = awsJobTimeoutConfig.getInProgressTimeoutInMinutes();
            awsJsonWriter.name("inProgressTimeoutInMinutes");
            awsJsonWriter.value(inProgressTimeoutInMinutes);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobTimeoutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobTimeoutConfigJsonMarshaller();
        }
        return instance;
    }
}
