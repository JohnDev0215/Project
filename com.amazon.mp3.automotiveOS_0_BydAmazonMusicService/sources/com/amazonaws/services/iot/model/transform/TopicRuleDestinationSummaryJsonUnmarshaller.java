package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.TopicRuleDestinationSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationSummaryJsonUnmarshaller implements Unmarshaller<TopicRuleDestinationSummary, JsonUnmarshallerContext> {
    private static TopicRuleDestinationSummaryJsonUnmarshaller instance;

    TopicRuleDestinationSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRuleDestinationSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRuleDestinationSummary topicRuleDestinationSummary = new TopicRuleDestinationSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("arn")) {
                topicRuleDestinationSummary.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                topicRuleDestinationSummary.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                topicRuleDestinationSummary.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastUpdatedAt")) {
                topicRuleDestinationSummary.setLastUpdatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("statusReason")) {
                topicRuleDestinationSummary.setStatusReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("httpUrlSummary")) {
                topicRuleDestinationSummary.setHttpUrlSummary(HttpUrlDestinationSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcDestinationSummary")) {
                topicRuleDestinationSummary.setVpcDestinationSummary(VpcDestinationSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRuleDestinationSummary;
    }

    public static TopicRuleDestinationSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
