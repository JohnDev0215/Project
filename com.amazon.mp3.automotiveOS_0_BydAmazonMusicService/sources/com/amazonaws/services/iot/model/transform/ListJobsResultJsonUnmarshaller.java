package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListJobsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobsResultJsonUnmarshaller implements Unmarshaller<ListJobsResult, JsonUnmarshallerContext> {
    private static ListJobsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListJobsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListJobsResult listJobsResult = new ListJobsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobs")) {
                listJobsResult.setJobs(new ListUnmarshaller(JobSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listJobsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listJobsResult;
    }

    public static ListJobsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListJobsResultJsonUnmarshaller();
        }
        return instance;
    }
}
