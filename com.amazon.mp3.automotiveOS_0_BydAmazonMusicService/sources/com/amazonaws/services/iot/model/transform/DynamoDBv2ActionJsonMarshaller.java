package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DynamoDBv2Action;
import com.amazonaws.services.iot.model.PutItemInput;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class DynamoDBv2ActionJsonMarshaller {
    private static DynamoDBv2ActionJsonMarshaller instance;

    DynamoDBv2ActionJsonMarshaller() {
    }

    public void marshall(DynamoDBv2Action dynamoDBv2Action, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (dynamoDBv2Action.getRoleArn() != null) {
            String roleArn = dynamoDBv2Action.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (dynamoDBv2Action.getPutItem() != null) {
            PutItemInput putItem = dynamoDBv2Action.getPutItem();
            awsJsonWriter.name("putItem");
            PutItemInputJsonMarshaller.getInstance().marshall(putItem, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static DynamoDBv2ActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DynamoDBv2ActionJsonMarshaller();
        }
        return instance;
    }
}
