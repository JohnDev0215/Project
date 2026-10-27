package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetTopicRuleResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleResultJsonUnmarshaller implements Unmarshaller<GetTopicRuleResult, JsonUnmarshallerContext> {
    private static GetTopicRuleResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetTopicRuleResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetTopicRuleResult getTopicRuleResult = new GetTopicRuleResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("ruleArn")) {
                getTopicRuleResult.setRuleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rule")) {
                getTopicRuleResult.setRule(TopicRuleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getTopicRuleResult;
    }

    public static GetTopicRuleResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetTopicRuleResultJsonUnmarshaller();
        }
        return instance;
    }
}
