package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetStatisticsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetStatisticsResultJsonUnmarshaller implements Unmarshaller<GetStatisticsResult, JsonUnmarshallerContext> {
    private static GetStatisticsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetStatisticsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetStatisticsResult getStatisticsResult = new GetStatisticsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("statistics")) {
                getStatisticsResult.setStatistics(StatisticsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getStatisticsResult;
    }

    public static GetStatisticsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetStatisticsResultJsonUnmarshaller();
        }
        return instance;
    }
}
