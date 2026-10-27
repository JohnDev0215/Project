package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchLogsAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CloudwatchLogsActionJsonUnmarshaller implements Unmarshaller<CloudwatchLogsAction, JsonUnmarshallerContext> {
    private static CloudwatchLogsActionJsonUnmarshaller instance;

    CloudwatchLogsActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CloudwatchLogsAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CloudwatchLogsAction cloudwatchLogsAction = new CloudwatchLogsAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                cloudwatchLogsAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("logGroupName")) {
                cloudwatchLogsAction.setLogGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cloudwatchLogsAction;
    }

    public static CloudwatchLogsActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchLogsActionJsonUnmarshaller();
        }
        return instance;
    }
}
