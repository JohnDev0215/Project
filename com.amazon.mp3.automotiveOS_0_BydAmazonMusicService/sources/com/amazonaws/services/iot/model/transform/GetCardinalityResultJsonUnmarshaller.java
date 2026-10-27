package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetCardinalityResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetCardinalityResultJsonUnmarshaller implements Unmarshaller<GetCardinalityResult, JsonUnmarshallerContext> {
    private static GetCardinalityResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetCardinalityResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetCardinalityResult getCardinalityResult = new GetCardinalityResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("cardinality")) {
                getCardinalityResult.setCardinality(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getCardinalityResult;
    }

    public static GetCardinalityResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetCardinalityResultJsonUnmarshaller();
        }
        return instance;
    }
}
