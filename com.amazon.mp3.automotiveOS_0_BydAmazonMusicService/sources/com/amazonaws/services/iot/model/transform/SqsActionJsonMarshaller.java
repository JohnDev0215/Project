package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SqsAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class SqsActionJsonMarshaller {
    private static SqsActionJsonMarshaller instance;

    SqsActionJsonMarshaller() {
    }

    public void marshall(SqsAction sqsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (sqsAction.getRoleArn() != null) {
            String roleArn = sqsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (sqsAction.getQueueUrl() != null) {
            String queueUrl = sqsAction.getQueueUrl();
            awsJsonWriter.name("queueUrl");
            awsJsonWriter.value(queueUrl);
        }
        if (sqsAction.getUseBase64() != null) {
            Boolean useBase64 = sqsAction.getUseBase64();
            awsJsonWriter.name("useBase64");
            awsJsonWriter.value(useBase64.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static SqsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SqsActionJsonMarshaller();
        }
        return instance;
    }
}
