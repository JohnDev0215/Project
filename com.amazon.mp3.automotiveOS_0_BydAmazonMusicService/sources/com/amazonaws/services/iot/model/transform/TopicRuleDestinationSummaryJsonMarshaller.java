package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.HttpUrlDestinationSummary;
import com.amazonaws.services.iot.model.TopicRuleDestinationSummary;
import com.amazonaws.services.iot.model.VpcDestinationSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationSummaryJsonMarshaller {
    private static TopicRuleDestinationSummaryJsonMarshaller instance;

    TopicRuleDestinationSummaryJsonMarshaller() {
    }

    public void marshall(TopicRuleDestinationSummary topicRuleDestinationSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (topicRuleDestinationSummary.getArn() != null) {
            String arn = topicRuleDestinationSummary.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        if (topicRuleDestinationSummary.getStatus() != null) {
            String status = topicRuleDestinationSummary.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (topicRuleDestinationSummary.getCreatedAt() != null) {
            Date createdAt = topicRuleDestinationSummary.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (topicRuleDestinationSummary.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = topicRuleDestinationSummary.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (topicRuleDestinationSummary.getStatusReason() != null) {
            String statusReason = topicRuleDestinationSummary.getStatusReason();
            awsJsonWriter.name("statusReason");
            awsJsonWriter.value(statusReason);
        }
        if (topicRuleDestinationSummary.getHttpUrlSummary() != null) {
            HttpUrlDestinationSummary httpUrlSummary = topicRuleDestinationSummary.getHttpUrlSummary();
            awsJsonWriter.name("httpUrlSummary");
            HttpUrlDestinationSummaryJsonMarshaller.getInstance().marshall(httpUrlSummary, awsJsonWriter);
        }
        if (topicRuleDestinationSummary.getVpcDestinationSummary() != null) {
            VpcDestinationSummary vpcDestinationSummary = topicRuleDestinationSummary.getVpcDestinationSummary();
            awsJsonWriter.name("vpcDestinationSummary");
            VpcDestinationSummaryJsonMarshaller.getInstance().marshall(vpcDestinationSummary, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static TopicRuleDestinationSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationSummaryJsonMarshaller();
        }
        return instance;
    }
}
