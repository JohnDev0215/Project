package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListCustomMetricsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListCustomMetricsResultJsonUnmarshaller implements Unmarshaller<ListCustomMetricsResult, JsonUnmarshallerContext> {
    private static ListCustomMetricsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListCustomMetricsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListCustomMetricsResult listCustomMetricsResult = new ListCustomMetricsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricNames")) {
                listCustomMetricsResult.setMetricNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listCustomMetricsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listCustomMetricsResult;
    }

    public static ListCustomMetricsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListCustomMetricsResultJsonUnmarshaller();
        }
        return instance;
    }
}
