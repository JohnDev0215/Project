package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KafkaAction;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class KafkaActionJsonMarshaller {
    private static KafkaActionJsonMarshaller instance;

    KafkaActionJsonMarshaller() {
    }

    public void marshall(KafkaAction kafkaAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (kafkaAction.getDestinationArn() != null) {
            String destinationArn = kafkaAction.getDestinationArn();
            awsJsonWriter.name("destinationArn");
            awsJsonWriter.value(destinationArn);
        }
        if (kafkaAction.getTopic() != null) {
            String topic = kafkaAction.getTopic();
            awsJsonWriter.name("topic");
            awsJsonWriter.value(topic);
        }
        if (kafkaAction.getKey() != null) {
            String key = kafkaAction.getKey();
            awsJsonWriter.name("key");
            awsJsonWriter.value(key);
        }
        if (kafkaAction.getPartition() != null) {
            String partition = kafkaAction.getPartition();
            awsJsonWriter.name("partition");
            awsJsonWriter.value(partition);
        }
        if (kafkaAction.getClientProperties() != null) {
            Map<String, String> clientProperties = kafkaAction.getClientProperties();
            awsJsonWriter.name("clientProperties");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : clientProperties.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }

    public static KafkaActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new KafkaActionJsonMarshaller();
        }
        return instance;
    }
}
