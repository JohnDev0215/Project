package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SqsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SqsActionJsonUnmarshaller implements Unmarshaller<SqsAction, JsonUnmarshallerContext> {
    private static SqsActionJsonUnmarshaller instance;

    SqsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SqsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SqsAction sqsAction = new SqsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                sqsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queueUrl")) {
                sqsAction.setQueueUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("useBase64")) {
                sqsAction.setUseBase64(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return sqsAction;
    }

    public static SqsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SqsActionJsonUnmarshaller();
        }
        return instance;
    }
}
