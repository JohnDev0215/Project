package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetTopicRuleDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleDestinationResultJsonUnmarshaller implements Unmarshaller<GetTopicRuleDestinationResult, JsonUnmarshallerContext> {
    private static GetTopicRuleDestinationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetTopicRuleDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetTopicRuleDestinationResult getTopicRuleDestinationResult = new GetTopicRuleDestinationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("topicRuleDestination")) {
                getTopicRuleDestinationResult.setTopicRuleDestination(TopicRuleDestinationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getTopicRuleDestinationResult;
    }

    public static GetTopicRuleDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetTopicRuleDestinationResultJsonUnmarshaller();
        }
        return instance;
    }
}
