package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.GetRetainedMessageResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetRetainedMessageResultJsonUnmarshaller implements Unmarshaller<GetRetainedMessageResult, JsonUnmarshallerContext> {
    private static GetRetainedMessageResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetRetainedMessageResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetRetainedMessageResult getRetainedMessageResult = new GetRetainedMessageResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("topic")) {
                getRetainedMessageResult.setTopic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("payload")) {
                getRetainedMessageResult.setPayload(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("qos")) {
                getRetainedMessageResult.setQos(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedTime")) {
                getRetainedMessageResult.setLastModifiedTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getRetainedMessageResult;
    }

    public static GetRetainedMessageResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetRetainedMessageResultJsonUnmarshaller();
        }
        return instance;
    }
}
