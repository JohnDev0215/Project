package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.FleetMetricNameAndArn;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class FleetMetricNameAndArnJsonUnmarshaller implements Unmarshaller<FleetMetricNameAndArn, JsonUnmarshallerContext> {
    private static FleetMetricNameAndArnJsonUnmarshaller instance;

    FleetMetricNameAndArnJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FleetMetricNameAndArn unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        FleetMetricNameAndArn fleetMetricNameAndArn = new FleetMetricNameAndArn();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricName")) {
                fleetMetricNameAndArn.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricArn")) {
                fleetMetricNameAndArn.setMetricArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return fleetMetricNameAndArn;
    }

    public static FleetMetricNameAndArnJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new FleetMetricNameAndArnJsonUnmarshaller();
        }
        return instance;
    }
}
