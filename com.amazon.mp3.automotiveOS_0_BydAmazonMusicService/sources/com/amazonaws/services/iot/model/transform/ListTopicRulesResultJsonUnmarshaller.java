package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTopicRulesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRulesResultJsonUnmarshaller implements Unmarshaller<ListTopicRulesResult, JsonUnmarshallerContext> {
    private static ListTopicRulesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTopicRulesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTopicRulesResult listTopicRulesResult = new ListTopicRulesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("rules")) {
                listTopicRulesResult.setRules(new ListUnmarshaller(TopicRuleListItemJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listTopicRulesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTopicRulesResult;
    }

    public static ListTopicRulesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTopicRulesResultJsonUnmarshaller();
        }
        return instance;
    }
}
