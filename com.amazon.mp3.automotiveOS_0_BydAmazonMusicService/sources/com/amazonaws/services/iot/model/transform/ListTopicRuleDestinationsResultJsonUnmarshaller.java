package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTopicRuleDestinationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRuleDestinationsResultJsonUnmarshaller implements Unmarshaller<ListTopicRuleDestinationsResult, JsonUnmarshallerContext> {
    private static ListTopicRuleDestinationsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTopicRuleDestinationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTopicRuleDestinationsResult listTopicRuleDestinationsResult = new ListTopicRuleDestinationsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("destinationSummaries")) {
                listTopicRuleDestinationsResult.setDestinationSummaries(new ListUnmarshaller(TopicRuleDestinationSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listTopicRuleDestinationsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTopicRuleDestinationsResult;
    }

    public static ListTopicRuleDestinationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTopicRuleDestinationsResultJsonUnmarshaller();
        }
        return instance;
    }
}
