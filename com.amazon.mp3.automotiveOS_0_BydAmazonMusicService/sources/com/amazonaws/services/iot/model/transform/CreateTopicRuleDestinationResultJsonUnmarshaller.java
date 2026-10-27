package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateTopicRuleDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleDestinationResultJsonUnmarshaller implements Unmarshaller<CreateTopicRuleDestinationResult, JsonUnmarshallerContext> {
    private static CreateTopicRuleDestinationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateTopicRuleDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateTopicRuleDestinationResult createTopicRuleDestinationResult = new CreateTopicRuleDestinationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("topicRuleDestination")) {
                createTopicRuleDestinationResult.setTopicRuleDestination(TopicRuleDestinationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createTopicRuleDestinationResult;
    }

    public static CreateTopicRuleDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateTopicRuleDestinationResultJsonUnmarshaller();
        }
        return instance;
    }
}
