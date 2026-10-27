package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListJobExecutionsForJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForJobResultJsonUnmarshaller implements Unmarshaller<ListJobExecutionsForJobResult, JsonUnmarshallerContext> {
    private static ListJobExecutionsForJobResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListJobExecutionsForJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListJobExecutionsForJobResult listJobExecutionsForJobResult = new ListJobExecutionsForJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("executionSummaries")) {
                listJobExecutionsForJobResult.setExecutionSummaries(new ListUnmarshaller(JobExecutionSummaryForJobJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listJobExecutionsForJobResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listJobExecutionsForJobResult;
    }

    public static ListJobExecutionsForJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListJobExecutionsForJobResultJsonUnmarshaller();
        }
        return instance;
    }
}
