package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RateIncreaseCriteria;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class RateIncreaseCriteriaJsonMarshaller {
    private static RateIncreaseCriteriaJsonMarshaller instance;

    RateIncreaseCriteriaJsonMarshaller() {
    }

    public void marshall(RateIncreaseCriteria rateIncreaseCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (rateIncreaseCriteria.getNumberOfNotifiedThings() != null) {
            Integer numberOfNotifiedThings = rateIncreaseCriteria.getNumberOfNotifiedThings();
            awsJsonWriter.name("numberOfNotifiedThings");
            awsJsonWriter.value(numberOfNotifiedThings);
        }
        if (rateIncreaseCriteria.getNumberOfSucceededThings() != null) {
            Integer numberOfSucceededThings = rateIncreaseCriteria.getNumberOfSucceededThings();
            awsJsonWriter.name("numberOfSucceededThings");
            awsJsonWriter.value(numberOfSucceededThings);
        }
        awsJsonWriter.endObject();
    }

    public static RateIncreaseCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RateIncreaseCriteriaJsonMarshaller();
        }
        return instance;
    }
}
