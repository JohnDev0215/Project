package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeEndpointResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEndpointResultJsonUnmarshaller implements Unmarshaller<DescribeEndpointResult, JsonUnmarshallerContext> {
    private static DescribeEndpointResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeEndpointResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeEndpointResult describeEndpointResult = new DescribeEndpointResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("endpointAddress")) {
                describeEndpointResult.setEndpointAddress(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeEndpointResult;
    }

    public static DescribeEndpointResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeEndpointResultJsonUnmarshaller();
        }
        return instance;
    }
}
