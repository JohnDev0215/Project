package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TermsAggregation;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TermsAggregationJsonUnmarshaller implements Unmarshaller<TermsAggregation, JsonUnmarshallerContext> {
    private static TermsAggregationJsonUnmarshaller instance;

    TermsAggregationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TermsAggregation unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TermsAggregation termsAggregation = new TermsAggregation();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("maxBuckets")) {
                termsAggregation.setMaxBuckets(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return termsAggregation;
    }

    public static TermsAggregationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TermsAggregationJsonUnmarshaller();
        }
        return instance;
    }
}
