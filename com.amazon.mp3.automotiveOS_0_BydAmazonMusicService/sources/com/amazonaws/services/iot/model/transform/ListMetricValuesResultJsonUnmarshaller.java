package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListMetricValuesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListMetricValuesResultJsonUnmarshaller implements Unmarshaller<ListMetricValuesResult, JsonUnmarshallerContext> {
    private static ListMetricValuesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListMetricValuesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListMetricValuesResult listMetricValuesResult = new ListMetricValuesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricDatumList")) {
                listMetricValuesResult.setMetricDatumList(new ListUnmarshaller(MetricDatumJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listMetricValuesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listMetricValuesResult;
    }

    public static ListMetricValuesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListMetricValuesResultJsonUnmarshaller();
        }
        return instance;
    }
}
