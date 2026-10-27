package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateFleetMetricResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateFleetMetricResultJsonUnmarshaller implements Unmarshaller<CreateFleetMetricResult, JsonUnmarshallerContext> {
    private static CreateFleetMetricResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateFleetMetricResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateFleetMetricResult createFleetMetricResult = new CreateFleetMetricResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricName")) {
                createFleetMetricResult.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricArn")) {
                createFleetMetricResult.setMetricArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createFleetMetricResult;
    }

    public static CreateFleetMetricResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateFleetMetricResultJsonUnmarshaller();
        }
        return instance;
    }
}
