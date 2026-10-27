package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DynamoDBAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DynamoDBActionJsonUnmarshaller implements Unmarshaller<DynamoDBAction, JsonUnmarshallerContext> {
    private static DynamoDBActionJsonUnmarshaller instance;

    DynamoDBActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DynamoDBAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DynamoDBAction dynamoDBAction = new DynamoDBAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("tableName")) {
                dynamoDBAction.setTableName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                dynamoDBAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("operation")) {
                dynamoDBAction.setOperation(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("hashKeyField")) {
                dynamoDBAction.setHashKeyField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("hashKeyValue")) {
                dynamoDBAction.setHashKeyValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("hashKeyType")) {
                dynamoDBAction.setHashKeyType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rangeKeyField")) {
                dynamoDBAction.setRangeKeyField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rangeKeyValue")) {
                dynamoDBAction.setRangeKeyValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rangeKeyType")) {
                dynamoDBAction.setRangeKeyType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("payloadField")) {
                dynamoDBAction.setPayloadField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return dynamoDBAction;
    }

    public static DynamoDBActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DynamoDBActionJsonUnmarshaller();
        }
        return instance;
    }
}
