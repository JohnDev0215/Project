package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobRateIncreaseCriteria;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobRateIncreaseCriteriaJsonMarshaller {
    private static AwsJobRateIncreaseCriteriaJsonMarshaller instance;

    AwsJobRateIncreaseCriteriaJsonMarshaller() {
    }

    public void marshall(AwsJobRateIncreaseCriteria awsJobRateIncreaseCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobRateIncreaseCriteria.getNumberOfNotifiedThings() != null) {
            Integer numberOfNotifiedThings = awsJobRateIncreaseCriteria.getNumberOfNotifiedThings();
            awsJsonWriter.name("numberOfNotifiedThings");
            awsJsonWriter.value(numberOfNotifiedThings);
        }
        if (awsJobRateIncreaseCriteria.getNumberOfSucceededThings() != null) {
            Integer numberOfSucceededThings = awsJobRateIncreaseCriteria.getNumberOfSucceededThings();
            awsJsonWriter.name("numberOfSucceededThings");
            awsJsonWriter.value(numberOfSucceededThings);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobRateIncreaseCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobRateIncreaseCriteriaJsonMarshaller();
        }
        return instance;
    }
}
