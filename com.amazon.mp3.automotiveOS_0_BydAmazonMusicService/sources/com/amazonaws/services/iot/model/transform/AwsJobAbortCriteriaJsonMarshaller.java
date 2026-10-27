package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobAbortCriteria;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobAbortCriteriaJsonMarshaller {
    private static AwsJobAbortCriteriaJsonMarshaller instance;

    AwsJobAbortCriteriaJsonMarshaller() {
    }

    public void marshall(AwsJobAbortCriteria awsJobAbortCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobAbortCriteria.getFailureType() != null) {
            String failureType = awsJobAbortCriteria.getFailureType();
            awsJsonWriter.name("failureType");
            awsJsonWriter.value(failureType);
        }
        if (awsJobAbortCriteria.getAction() != null) {
            String action = awsJobAbortCriteria.getAction();
            awsJsonWriter.name("action");
            awsJsonWriter.value(action);
        }
        if (awsJobAbortCriteria.getThresholdPercentage() != null) {
            Double thresholdPercentage = awsJobAbortCriteria.getThresholdPercentage();
            awsJsonWriter.name("thresholdPercentage");
            awsJsonWriter.value(thresholdPercentage);
        }
        if (awsJobAbortCriteria.getMinNumberOfExecutedThings() != null) {
            Integer minNumberOfExecutedThings = awsJobAbortCriteria.getMinNumberOfExecutedThings();
            awsJsonWriter.name("minNumberOfExecutedThings");
            awsJsonWriter.value(minNumberOfExecutedThings);
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobAbortCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobAbortCriteriaJsonMarshaller();
        }
        return instance;
    }
}
