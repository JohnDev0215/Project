package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Statistics;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class StatisticsJsonUnmarshaller implements Unmarshaller<Statistics, JsonUnmarshallerContext> {
    private static StatisticsJsonUnmarshaller instance;

    StatisticsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Statistics unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Statistics statistics = new Statistics();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("count")) {
                statistics.setCount(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("average")) {
                statistics.setAverage(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("sum")) {
                statistics.setSum(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("minimum")) {
                statistics.setMinimum(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("maximum")) {
                statistics.setMaximum(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("sumOfSquares")) {
                statistics.setSumOfSquares(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("variance")) {
                statistics.setVariance(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stdDeviation")) {
                statistics.setStdDeviation(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return statistics;
    }

    public static StatisticsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StatisticsJsonUnmarshaller();
        }
        return instance;
    }
}
