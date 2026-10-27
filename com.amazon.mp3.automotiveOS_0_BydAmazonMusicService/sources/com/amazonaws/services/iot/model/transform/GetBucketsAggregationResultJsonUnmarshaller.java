package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetBucketsAggregationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetBucketsAggregationResultJsonUnmarshaller implements Unmarshaller<GetBucketsAggregationResult, JsonUnmarshallerContext> {
    private static GetBucketsAggregationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetBucketsAggregationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetBucketsAggregationResult getBucketsAggregationResult = new GetBucketsAggregationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("totalCount")) {
                getBucketsAggregationResult.setTotalCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("buckets")) {
                getBucketsAggregationResult.setBuckets(new ListUnmarshaller(BucketJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getBucketsAggregationResult;
    }

    public static GetBucketsAggregationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetBucketsAggregationResultJsonUnmarshaller();
        }
        return instance;
    }
}
