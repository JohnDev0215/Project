package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Action;
import com.amazonaws.services.iot.model.TopicRule;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleJsonMarshaller {
    private static TopicRuleJsonMarshaller instance;

    TopicRuleJsonMarshaller() {
    }

    public void marshall(TopicRule topicRule, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (topicRule.getRuleName() != null) {
            String ruleName = topicRule.getRuleName();
            awsJsonWriter.name("ruleName");
            awsJsonWriter.value(ruleName);
        }
        if (topicRule.getSql() != null) {
            String sql = topicRule.getSql();
            awsJsonWriter.name("sql");
            awsJsonWriter.value(sql);
        }
        if (topicRule.getDescription() != null) {
            String description = topicRule.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (topicRule.getCreatedAt() != null) {
            Date createdAt = topicRule.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (topicRule.getActions() != null) {
            List<Action> actions = topicRule.getActions();
            awsJsonWriter.name("actions");
            awsJsonWriter.beginArray();
            for (Action action : actions) {
                if (action != null) {
                    ActionJsonMarshaller.getInstance().marshall(action, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (topicRule.getRuleDisabled() != null) {
            Boolean ruleDisabled = topicRule.getRuleDisabled();
            awsJsonWriter.name("ruleDisabled");
            awsJsonWriter.value(ruleDisabled.booleanValue());
        }
        if (topicRule.getAwsIotSqlVersion() != null) {
            String awsIotSqlVersion = topicRule.getAwsIotSqlVersion();
            awsJsonWriter.name("awsIotSqlVersion");
            awsJsonWriter.value(awsIotSqlVersion);
        }
        if (topicRule.getErrorAction() != null) {
            Action errorAction = topicRule.getErrorAction();
            awsJsonWriter.name("errorAction");
            ActionJsonMarshaller.getInstance().marshall(errorAction, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static TopicRuleJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleJsonMarshaller();
        }
        return instance;
    }
}
