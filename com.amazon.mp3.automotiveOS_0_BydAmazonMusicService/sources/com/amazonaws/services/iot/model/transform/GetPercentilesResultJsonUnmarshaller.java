package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetPercentilesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetPercentilesResultJsonUnmarshaller implements Unmarshaller<GetPercentilesResult, JsonUnmarshallerContext> {
    private static GetPercentilesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetPercentilesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetPercentilesResult getPercentilesResult = new GetPercentilesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("percentiles")) {
                getPercentilesResult.setPercentiles(new ListUnmarshaller(PercentPairJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getPercentilesResult;
    }

    public static GetPercentilesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetPercentilesResultJsonUnmarshaller();
        }
        return instance;
    }
}
