package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TopicRulePayload;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class TopicRulePayloadJsonUnmarshaller implements Unmarshaller<TopicRulePayload, JsonUnmarshallerContext> {
    private static TopicRulePayloadJsonUnmarshaller instance;

    TopicRulePayloadJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRulePayload unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRulePayload topicRulePayload = new TopicRulePayload();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("sql")) {
                topicRulePayload.setSql(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                topicRulePayload.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actions")) {
                topicRulePayload.setActions(new ListUnmarshaller(ActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("ruleDisabled")) {
                topicRulePayload.setRuleDisabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsIotSqlVersion")) {
                topicRulePayload.setAwsIotSqlVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorAction")) {
                topicRulePayload.setErrorAction(ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRulePayload;
    }

    public static TopicRulePayloadJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRulePayloadJsonUnmarshaller();
        }
        return instance;
    }
}
