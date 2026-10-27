package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotEventsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class IotEventsActionJsonUnmarshaller implements Unmarshaller<IotEventsAction, JsonUnmarshallerContext> {
    private static IotEventsActionJsonUnmarshaller instance;

    IotEventsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IotEventsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IotEventsAction iotEventsAction = new IotEventsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("inputName")) {
                iotEventsAction.setInputName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("messageId")) {
                iotEventsAction.setMessageId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("batchMode")) {
                iotEventsAction.setBatchMode(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                iotEventsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return iotEventsAction;
    }

    public static IotEventsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IotEventsActionJsonUnmarshaller();
        }
        return instance;
    }
}
