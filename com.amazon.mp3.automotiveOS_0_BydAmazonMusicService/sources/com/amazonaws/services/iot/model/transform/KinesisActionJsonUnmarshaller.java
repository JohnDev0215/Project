package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KinesisAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class KinesisActionJsonUnmarshaller implements Unmarshaller<KinesisAction, JsonUnmarshallerContext> {
    private static KinesisActionJsonUnmarshaller instance;

    KinesisActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public KinesisAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        KinesisAction kinesisAction = new KinesisAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                kinesisAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("streamName")) {
                kinesisAction.setStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("partitionKey")) {
                kinesisAction.setPartitionKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return kinesisAction;
    }

    public static KinesisActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KinesisActionJsonUnmarshaller();
        }
        return instance;
    }
}
