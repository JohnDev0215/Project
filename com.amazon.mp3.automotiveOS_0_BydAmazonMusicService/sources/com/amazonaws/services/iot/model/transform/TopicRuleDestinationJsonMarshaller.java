package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.HttpUrlDestinationProperties;
import com.amazonaws.services.iot.model.TopicRuleDestination;
import com.amazonaws.services.iot.model.VpcDestinationProperties;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationJsonMarshaller {
    private static TopicRuleDestinationJsonMarshaller instance;

    TopicRuleDestinationJsonMarshaller() {
    }

    public void marshall(TopicRuleDestination topicRuleDestination, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (topicRuleDestination.getArn() != null) {
            String arn = topicRuleDestination.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        if (topicRuleDestination.getStatus() != null) {
            String status = topicRuleDestination.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (topicRuleDestination.getCreatedAt() != null) {
            Date createdAt = topicRuleDestination.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (topicRuleDestination.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = topicRuleDestination.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (topicRuleDestination.getStatusReason() != null) {
            String statusReason = topicRuleDestination.getStatusReason();
            awsJsonWriter.name("statusReason");
            awsJsonWriter.value(statusReason);
        }
        if (topicRuleDestination.getHttpUrlProperties() != null) {
            HttpUrlDestinationProperties httpUrlProperties = topicRuleDestination.getHttpUrlProperties();
            awsJsonWriter.name("httpUrlProperties");
            HttpUrlDestinationPropertiesJsonMarshaller.getInstance().marshall(httpUrlProperties, awsJsonWriter);
        }
        if (topicRuleDestination.getVpcProperties() != null) {
            VpcDestinationProperties vpcProperties = topicRuleDestination.getVpcProperties();
            awsJsonWriter.name("vpcProperties");
            VpcDestinationPropertiesJsonMarshaller.getInstance().marshall(vpcProperties, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static TopicRuleDestinationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationJsonMarshaller();
        }
        return instance;
    }
}
