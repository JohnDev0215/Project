package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteTopicRuleDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteTopicRuleDestinationResultJsonUnmarshaller implements Unmarshaller<DeleteTopicRuleDestinationResult, JsonUnmarshallerContext> {
    private static DeleteTopicRuleDestinationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteTopicRuleDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteTopicRuleDestinationResult();
    }

    public static DeleteTopicRuleDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteTopicRuleDestinationResultJsonUnmarshaller();
        }
        return instance;
    }
}
