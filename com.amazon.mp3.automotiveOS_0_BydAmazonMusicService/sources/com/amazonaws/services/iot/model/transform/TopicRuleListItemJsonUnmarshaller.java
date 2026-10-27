package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TopicRuleListItem;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleListItemJsonUnmarshaller implements Unmarshaller<TopicRuleListItem, JsonUnmarshallerContext> {
    private static TopicRuleListItemJsonUnmarshaller instance;

    TopicRuleListItemJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRuleListItem unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRuleListItem topicRuleListItem = new TopicRuleListItem();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("ruleArn")) {
                topicRuleListItem.setRuleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ruleName")) {
                topicRuleListItem.setRuleName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("topicPattern")) {
                topicRuleListItem.setTopicPattern(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                topicRuleListItem.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ruleDisabled")) {
                topicRuleListItem.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRuleListItem;
    }

    public static TopicRuleListItemJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleListItemJsonUnmarshaller();
        }
        return instance;
    }
}
