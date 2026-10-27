package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RepublishAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class RepublishActionJsonMarshaller {
    private static RepublishActionJsonMarshaller instance;

    RepublishActionJsonMarshaller() {
    }

    public void marshall(RepublishAction republishAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (republishAction.getRoleArn() != null) {
            String roleArn = republishAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (republishAction.getTopic() != null) {
            String topic = republishAction.getTopic();
            awsJsonWriter.name("topic");
            awsJsonWriter.value(topic);
        }
        if (republishAction.getQos() != null) {
            Integer qos = republishAction.getQos();
            awsJsonWriter.name("qos");
            awsJsonWriter.value(qos);
        }
        awsJsonWriter.endObject();
    }

    public static RepublishActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RepublishActionJsonMarshaller();
        }
        return instance;
    }
}
