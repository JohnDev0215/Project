package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuthorizerResultJsonUnmarshaller implements Unmarshaller<DescribeAuthorizerResult, JsonUnmarshallerContext> {
    private static DescribeAuthorizerResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAuthorizerResult describeAuthorizerResult = new DescribeAuthorizerResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("authorizerDescription")) {
                describeAuthorizerResult.setAuthorizerDescription(AuthorizerDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAuthorizerResult;
    }

    public static DescribeAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }
}
