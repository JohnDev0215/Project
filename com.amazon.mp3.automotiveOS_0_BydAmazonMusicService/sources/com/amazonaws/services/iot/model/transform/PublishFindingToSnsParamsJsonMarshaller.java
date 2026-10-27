package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PublishFindingToSnsParams;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class PublishFindingToSnsParamsJsonMarshaller {
    private static PublishFindingToSnsParamsJsonMarshaller instance;

    PublishFindingToSnsParamsJsonMarshaller() {
    }

    public void marshall(PublishFindingToSnsParams publishFindingToSnsParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (publishFindingToSnsParams.getTopicArn() != null) {
            String topicArn = publishFindingToSnsParams.getTopicArn();
            awsJsonWriter.name("topicArn");
            awsJsonWriter.value(topicArn);
        }
        awsJsonWriter.endObject();
    }

    public static PublishFindingToSnsParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PublishFindingToSnsParamsJsonMarshaller();
        }
        return instance;
    }
}
