package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TopicRuleDestinationConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TopicRuleDestinationConfigurationJsonUnmarshaller implements Unmarshaller<TopicRuleDestinationConfiguration, JsonUnmarshallerContext> {
    private static TopicRuleDestinationConfigurationJsonUnmarshaller instance;

    TopicRuleDestinationConfigurationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TopicRuleDestinationConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TopicRuleDestinationConfiguration topicRuleDestinationConfiguration = new TopicRuleDestinationConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("httpUrlConfiguration")) {
                topicRuleDestinationConfiguration.setHttpUrlConfiguration(HttpUrlDestinationConfigurationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcConfiguration")) {
                topicRuleDestinationConfiguration.setVpcConfiguration(VpcDestinationConfigurationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return topicRuleDestinationConfiguration;
    }

    public static TopicRuleDestinationConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TopicRuleDestinationConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
