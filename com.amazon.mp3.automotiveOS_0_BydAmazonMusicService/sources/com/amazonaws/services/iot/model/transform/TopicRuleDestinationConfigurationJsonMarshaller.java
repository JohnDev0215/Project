package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.HttpUrlDestinationConfiguration;
import com.amazonaws.services.iot.model.TopicRuleDestinationConfiguration;
import com.amazonaws.services.iot.model.VpcDestinationConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationConfigurationJsonMarshaller {
    private static TopicRuleDestinationConfigurationJsonMarshaller instance;

    TopicRuleDestinationConfigurationJsonMarshaller() {
    }

    public void marshall(TopicRuleDestinationConfiguration topicRuleDestinationConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (topicRuleDestinationConfiguration.getHttpUrlConfiguration() != null) {
            HttpUrlDestinationConfiguration httpUrlConfiguration = topicRuleDestinationConfiguration.getHttpUrlConfiguration();
            awsJsonWriter.name("httpUrlConfiguration");
            HttpUrlDestinationConfigurationJsonMarshaller.getInstance().marshall(httpUrlConfiguration, awsJsonWriter);
        }
        if (topicRuleDestinationConfiguration.getVpcConfiguration() != null) {
            VpcDestinationConfiguration vpcConfiguration = topicRuleDestinationConfiguration.getVpcConfiguration();
            awsJsonWriter.name("vpcConfiguration");
            VpcDestinationConfigurationJsonMarshaller.getInstance().marshall(vpcConfiguration, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static TopicRuleDestinationConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationConfigurationJsonMarshaller();
        }
        return instance;
    }
}
