package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RetryCriteria;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class RetryCriteriaJsonMarshaller {
    private static RetryCriteriaJsonMarshaller instance;

    RetryCriteriaJsonMarshaller() {
    }

    public void marshall(RetryCriteria retryCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (retryCriteria.getFailureType() != null) {
            String failureType = retryCriteria.getFailureType();
            awsJsonWriter.name("failureType");
            awsJsonWriter.value(failureType);
        }
        if (retryCriteria.getNumberOfRetries() != null) {
            Integer numberOfRetries = retryCriteria.getNumberOfRetries();
            awsJsonWriter.name("numberOfRetries");
            awsJsonWriter.value(numberOfRetries);
        }
        awsJsonWriter.endObject();
    }

    public static RetryCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RetryCriteriaJsonMarshaller();
        }
        return instance;
    }
}
