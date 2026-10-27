package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeFleetMetricResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeFleetMetricResultJsonUnmarshaller implements Unmarshaller<DescribeFleetMetricResult, JsonUnmarshallerContext> {
    private static DescribeFleetMetricResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeFleetMetricResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeFleetMetricResult describeFleetMetricResult = new DescribeFleetMetricResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("metricName")) {
                describeFleetMetricResult.setMetricName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryString")) {
                describeFleetMetricResult.setQueryString(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("aggregationType")) {
                describeFleetMetricResult.setAggregationType(AggregationTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("period")) {
                describeFleetMetricResult.setPeriod(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("aggregationField")) {
                describeFleetMetricResult.setAggregationField(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                describeFleetMetricResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryVersion")) {
                describeFleetMetricResult.setQueryVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("indexName")) {
                describeFleetMetricResult.setIndexName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                describeFleetMetricResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                describeFleetMetricResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("unit")) {
                describeFleetMetricResult.setUnit(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                describeFleetMetricResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricArn")) {
                describeFleetMetricResult.setMetricArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeFleetMetricResult;
    }

    public static DescribeFleetMetricResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeFleetMetricResultJsonUnmarshaller();
        }
        return instance;
    }
}
