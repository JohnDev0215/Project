package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchMetricAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CloudwatchMetricActionJsonUnmarshaller implements Unmarshaller<CloudwatchMetricAction, JsonUnmarshallerContext> {
    private static CloudwatchMetricActionJsonUnmarshaller instance;

    CloudwatchMetricActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CloudwatchMetricAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CloudwatchMetricAction cloudwatchMetricAction = new CloudwatchMetricAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                cloudwatchMetricAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricNamespace")) {
                cloudwatchMetricAction.setMetricNamespace(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricName")) {
                cloudwatchMetricAction.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricValue")) {
                cloudwatchMetricAction.setMetricValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricUnit")) {
                cloudwatchMetricAction.setMetricUnit(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricTimestamp")) {
                cloudwatchMetricAction.setMetricTimestamp(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cloudwatchMetricAction;
    }

    public static CloudwatchMetricActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchMetricActionJsonUnmarshaller();
        }
        return instance;
    }
}
