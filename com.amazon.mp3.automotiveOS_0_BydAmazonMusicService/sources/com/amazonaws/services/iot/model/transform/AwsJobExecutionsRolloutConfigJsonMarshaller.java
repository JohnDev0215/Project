package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.AwsJobExponentialRolloutRate;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobExecutionsRolloutConfigJsonMarshaller {
    private static AwsJobExecutionsRolloutConfigJsonMarshaller instance;

    AwsJobExecutionsRolloutConfigJsonMarshaller() {
    }

    public void marshall(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobExecutionsRolloutConfig.getMaximumPerMinute() != null) {
            Integer maximumPerMinute = awsJobExecutionsRolloutConfig.getMaximumPerMinute();
            awsJsonWriter.name("maximumPerMinute");
            awsJsonWriter.value(maximumPerMinute);
        }
        if (awsJobExecutionsRolloutConfig.getExponentialRate() != null) {
            AwsJobExponentialRolloutRate exponentialRate = awsJobExecutionsRolloutConfig.getExponentialRate();
            awsJsonWriter.name("exponentialRate");
            AwsJobExponentialRolloutRateJsonMarshaller.getInstance().marshall(exponentialRate, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobExecutionsRolloutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobExecutionsRolloutConfigJsonMarshaller();
        }
        return instance;
    }
}
