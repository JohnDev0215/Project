package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.FirehoseAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class FirehoseActionJsonMarshaller {
    private static FirehoseActionJsonMarshaller instance;

    FirehoseActionJsonMarshaller() {
    }

    public void marshall(FirehoseAction firehoseAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (firehoseAction.getRoleArn() != null) {
            String roleArn = firehoseAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (firehoseAction.getDeliveryStreamName() != null) {
            String deliveryStreamName = firehoseAction.getDeliveryStreamName();
            awsJsonWriter.name("deliveryStreamName");
            awsJsonWriter.value(deliveryStreamName);
        }
        if (firehoseAction.getSeparator() != null) {
            String separator = firehoseAction.getSeparator();
            awsJsonWriter.name("separator");
            awsJsonWriter.value(separator);
        }
        if (firehoseAction.getBatchMode() != null) {
            Boolean batchMode = firehoseAction.getBatchMode();
            awsJsonWriter.name("batchMode");
            awsJsonWriter.value(batchMode.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static FirehoseActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new FirehoseActionJsonMarshaller();
        }
        return instance;
    }
}
