package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TermsAggregation;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TermsAggregationJsonMarshaller {
    private static TermsAggregationJsonMarshaller instance;

    TermsAggregationJsonMarshaller() {
    }

    public void marshall(TermsAggregation termsAggregation, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (termsAggregation.getMaxBuckets() != null) {
            Integer maxBuckets = termsAggregation.getMaxBuckets();
            awsJsonWriter.name("maxBuckets");
            awsJsonWriter.value(maxBuckets);
        }
        awsJsonWriter.endObject();
    }

    public static TermsAggregationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TermsAggregationJsonMarshaller();
        }
        return instance;
    }
}
