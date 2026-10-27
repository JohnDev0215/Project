package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TopicRule;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleJsonUnmarshaller implements Unmarshaller<TopicRule, JsonUnmarshallerContext> {
    private static TopicRuleJsonUnmarshaller instance;

    TopicRuleJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRule unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRule topicRule = new TopicRule();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("ruleName")) {
                topicRule.setRuleName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("sql")) {
                topicRule.setSql(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                topicRule.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                topicRule.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actions")) {
                topicRule.setActions(new ListUnmarshaller(ActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ruleDisabled")) {
                topicRule.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsIotSqlVersion")) {
                topicRule.setAwsIotSqlVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorAction")) {
                topicRule.setErrorAction(ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRule;
    }

    public static TopicRuleJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleJsonUnmarshaller();
        }
        return instance;
    }
}
