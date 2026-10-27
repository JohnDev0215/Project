package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BucketsAggregationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BucketsAggregationTypeJsonUnmarshaller implements Unmarshaller<BucketsAggregationType, JsonUnmarshallerContext> {
    private static BucketsAggregationTypeJsonUnmarshaller instance;

    BucketsAggregationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BucketsAggregationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BucketsAggregationType bucketsAggregationType = new BucketsAggregationType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("termsAggregation")) {
                bucketsAggregationType.setTermsAggregation(TermsAggregationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return bucketsAggregationType;
    }

    public static BucketsAggregationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BucketsAggregationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
