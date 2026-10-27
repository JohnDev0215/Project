package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListJobExecutionsForThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForThingResultJsonUnmarshaller implements Unmarshaller<ListJobExecutionsForThingResult, JsonUnmarshallerContext> {
    private static ListJobExecutionsForThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListJobExecutionsForThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListJobExecutionsForThingResult listJobExecutionsForThingResult = new ListJobExecutionsForThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("executionSummaries")) {
                listJobExecutionsForThingResult.setExecutionSummaries(new ListUnmarshaller(JobExecutionSummaryForThingJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listJobExecutionsForThingResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listJobExecutionsForThingResult;
    }

    public static ListJobExecutionsForThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListJobExecutionsForThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
