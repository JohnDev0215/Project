package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.RetainedMessageSummary;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class RetainedMessageSummaryJsonMarshaller {
    private static RetainedMessageSummaryJsonMarshaller instance;

    RetainedMessageSummaryJsonMarshaller() {
    }

    public void marshall(RetainedMessageSummary retainedMessageSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (retainedMessageSummary.getTopic() != null) {
            String topic = retainedMessageSummary.getTopic();
            awsJsonWriter.name("topic");
            awsJsonWriter.value(topic);
        }
        if (retainedMessageSummary.getPayloadSize() != null) {
            Long payloadSize = retainedMessageSummary.getPayloadSize();
            awsJsonWriter.name("payloadSize");
            awsJsonWriter.value(payloadSize);
        }
        if (retainedMessageSummary.getQos() != null) {
            Integer qos = retainedMessageSummary.getQos();
            awsJsonWriter.name("qos");
            awsJsonWriter.value(qos);
        }
        if (retainedMessageSummary.getLastModifiedTime() != null) {
            Long lastModifiedTime = retainedMessageSummary.getLastModifiedTime();
            awsJsonWriter.name("lastModifiedTime");
            awsJsonWriter.value(lastModifiedTime);
        }
        awsJsonWriter.endObject();
    }

    public static RetainedMessageSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RetainedMessageSummaryJsonMarshaller();
        }
        return instance;
    }
}
