package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExponentialRolloutRate;
import com.amazonaws.services.iot.model.AwsJobRateIncreaseCriteria;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobExponentialRolloutRateJsonMarshaller {
    private static AwsJobExponentialRolloutRateJsonMarshaller instance;

    AwsJobExponentialRolloutRateJsonMarshaller() {
    }

    public void marshall(AwsJobExponentialRolloutRate awsJobExponentialRolloutRate, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobExponentialRolloutRate.getBaseRatePerMinute() != null) {
            Integer baseRatePerMinute = awsJobExponentialRolloutRate.getBaseRatePerMinute();
            awsJsonWriter.name("baseRatePerMinute");
            awsJsonWriter.value(baseRatePerMinute);
        }
        if (awsJobExponentialRolloutRate.getIncrementFactor() != null) {
            Double incrementFactor = awsJobExponentialRolloutRate.getIncrementFactor();
            awsJsonWriter.name("incrementFactor");
            awsJsonWriter.value(incrementFactor);
        }
        if (awsJobExponentialRolloutRate.getRateIncreaseCriteria() != null) {
            AwsJobRateIncreaseCriteria rateIncreaseCriteria = awsJobExponentialRolloutRate.getRateIncreaseCriteria();
            awsJsonWriter.name("rateIncreaseCriteria");
            AwsJobRateIncreaseCriteriaJsonMarshaller.getInstance().marshall(rateIncreaseCriteria, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobExponentialRolloutRateJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobExponentialRolloutRateJsonMarshaller();
        }
        return instance;
    }
}
