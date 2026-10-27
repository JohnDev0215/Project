package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BucketsAggregationType;
import com.amazonaws.services.iot.model.TermsAggregation;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class BucketsAggregationTypeJsonMarshaller {
    private static BucketsAggregationTypeJsonMarshaller instance;

    BucketsAggregationTypeJsonMarshaller() {
    }

    public void marshall(BucketsAggregationType bucketsAggregationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (bucketsAggregationType.getTermsAggregation() != null) {
            TermsAggregation termsAggregation = bucketsAggregationType.getTermsAggregation();
            awsJsonWriter.name("termsAggregation");
            TermsAggregationJsonMarshaller.getInstance().marshall(termsAggregation, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static BucketsAggregationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BucketsAggregationTypeJsonMarshaller();
        }
        return instance;
    }
}
