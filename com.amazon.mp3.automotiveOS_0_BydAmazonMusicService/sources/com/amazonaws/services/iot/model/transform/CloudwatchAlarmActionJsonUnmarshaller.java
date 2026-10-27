package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchAlarmAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CloudwatchAlarmActionJsonUnmarshaller implements Unmarshaller<CloudwatchAlarmAction, JsonUnmarshallerContext> {
    private static CloudwatchAlarmActionJsonUnmarshaller instance;

    CloudwatchAlarmActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CloudwatchAlarmAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CloudwatchAlarmAction cloudwatchAlarmAction = new CloudwatchAlarmAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                cloudwatchAlarmAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("alarmName")) {
                cloudwatchAlarmAction.setAlarmName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stateReason")) {
                cloudwatchAlarmAction.setStateReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stateValue")) {
                cloudwatchAlarmAction.setStateValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cloudwatchAlarmAction;
    }

    public static CloudwatchAlarmActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchAlarmActionJsonUnmarshaller();
        }
        return instance;
    }
}
