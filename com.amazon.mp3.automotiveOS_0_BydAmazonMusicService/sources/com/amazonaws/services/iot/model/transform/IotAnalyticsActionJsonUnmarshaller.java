package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotAnalyticsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class IotAnalyticsActionJsonUnmarshaller implements Unmarshaller<IotAnalyticsAction, JsonUnmarshallerContext> {
    private static IotAnalyticsActionJsonUnmarshaller instance;

    IotAnalyticsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IotAnalyticsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IotAnalyticsAction iotAnalyticsAction = new IotAnalyticsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("channelArn")) {
                iotAnalyticsAction.setChannelArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("channelName")) {
                iotAnalyticsAction.setChannelName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("batchMode")) {
                iotAnalyticsAction.setBatchMode(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                iotAnalyticsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return iotAnalyticsAction;
    }

    public static IotAnalyticsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IotAnalyticsActionJsonUnmarshaller();
        }
        return instance;
    }
}
