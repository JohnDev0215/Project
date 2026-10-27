package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateTopicRuleDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateTopicRuleDestinationResultJsonUnmarshaller implements Unmarshaller<UpdateTopicRuleDestinationResult, JsonUnmarshallerContext> {
    private static UpdateTopicRuleDestinationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateTopicRuleDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateTopicRuleDestinationResult();
    }

    public static UpdateTopicRuleDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateTopicRuleDestinationResultJsonUnmarshaller();
        }
        return instance;
    }
}
