package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateCustomMetricResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCustomMetricResultJsonUnmarshaller implements Unmarshaller<UpdateCustomMetricResult, JsonUnmarshallerContext> {
    private static UpdateCustomMetricResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateCustomMetricResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateCustomMetricResult updateCustomMetricResult = new UpdateCustomMetricResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricName")) {
                updateCustomMetricResult.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricArn")) {
                updateCustomMetricResult.setMetricArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricType")) {
                updateCustomMetricResult.setMetricType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("displayName")) {
                updateCustomMetricResult.setDisplayName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                updateCustomMetricResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                updateCustomMetricResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateCustomMetricResult;
    }

    public static UpdateCustomMetricResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateCustomMetricResultJsonUnmarshaller();
        }
        return instance;
    }
}
