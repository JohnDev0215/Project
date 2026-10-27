package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.TopicRuleDestination;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationJsonUnmarshaller implements Unmarshaller<TopicRuleDestination, JsonUnmarshallerContext> {
    private static TopicRuleDestinationJsonUnmarshaller instance;

    TopicRuleDestinationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRuleDestination unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRuleDestination topicRuleDestination = new TopicRuleDestination();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("arn")) {
                topicRuleDestination.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                topicRuleDestination.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                topicRuleDestination.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastUpdatedAt")) {
                topicRuleDestination.setLastUpdatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("statusReason")) {
                topicRuleDestination.setStatusReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("httpUrlProperties")) {
                topicRuleDestination.setHttpUrlProperties(HttpUrlDestinationPropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcProperties")) {
                topicRuleDestination.setVpcProperties(VpcDestinationPropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRuleDestination;
    }

    public static TopicRuleDestinationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationJsonUnmarshaller();
        }
        return instance;
    }
}
