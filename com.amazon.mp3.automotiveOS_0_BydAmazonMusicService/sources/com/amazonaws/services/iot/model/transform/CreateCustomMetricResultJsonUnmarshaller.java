package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateCustomMetricResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCustomMetricResultJsonUnmarshaller implements Unmarshaller<CreateCustomMetricResult, JsonUnmarshallerContext> {
    private static CreateCustomMetricResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateCustomMetricResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateCustomMetricResult createCustomMetricResult = new CreateCustomMetricResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricName")) {
                createCustomMetricResult.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricArn")) {
                createCustomMetricResult.setMetricArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createCustomMetricResult;
    }

    public static CreateCustomMetricResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateCustomMetricResultJsonUnmarshaller();
        }
        return instance;
    }
}
