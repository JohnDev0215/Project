package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ConfirmTopicRuleDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmTopicRuleDestinationResultJsonUnmarshaller implements Unmarshaller<ConfirmTopicRuleDestinationResult, JsonUnmarshallerContext> {
    private static ConfirmTopicRuleDestinationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ConfirmTopicRuleDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new ConfirmTopicRuleDestinationResult();
    }

    public static ConfirmTopicRuleDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ConfirmTopicRuleDestinationResultJsonUnmarshaller();
        }
        return instance;
    }
}
