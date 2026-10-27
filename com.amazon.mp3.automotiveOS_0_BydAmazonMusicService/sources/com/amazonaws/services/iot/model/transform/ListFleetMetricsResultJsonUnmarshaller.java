package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListFleetMetricsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListFleetMetricsResultJsonUnmarshaller implements Unmarshaller<ListFleetMetricsResult, JsonUnmarshallerContext> {
    private static ListFleetMetricsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListFleetMetricsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListFleetMetricsResult listFleetMetricsResult = new ListFleetMetricsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("fleetMetrics")) {
                listFleetMetricsResult.setFleetMetrics(new ListUnmarshaller(FleetMetricNameAndArnJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listFleetMetricsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listFleetMetricsResult;
    }

    public static ListFleetMetricsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListFleetMetricsResultJsonUnmarshaller();
        }
        return instance;
    }
}
