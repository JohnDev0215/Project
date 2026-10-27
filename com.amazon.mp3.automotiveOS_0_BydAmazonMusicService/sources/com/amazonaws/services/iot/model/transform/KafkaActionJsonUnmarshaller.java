package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KafkaAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class KafkaActionJsonUnmarshaller implements Unmarshaller<KafkaAction, JsonUnmarshallerContext> {
    private static KafkaActionJsonUnmarshaller instance;

    KafkaActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public KafkaAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        KafkaAction kafkaAction = new KafkaAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("destinationArn")) {
                kafkaAction.setDestinationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("topic")) {
                kafkaAction.setTopic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("key")) {
                kafkaAction.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("partition")) {
                kafkaAction.setPartition(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("clientProperties")) {
                kafkaAction.setClientProperties(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return kafkaAction;
    }

    public static KafkaActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KafkaActionJsonUnmarshaller();
        }
        return instance;
    }
}
