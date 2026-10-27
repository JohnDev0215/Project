package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummaryForThing;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionSummaryForThingJsonUnmarshaller implements Unmarshaller<JobExecutionSummaryForThing, JsonUnmarshallerContext> {
    private static JobExecutionSummaryForThingJsonUnmarshaller instance;

    JobExecutionSummaryForThingJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecutionSummaryForThing unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecutionSummaryForThing jobExecutionSummaryForThing = new JobExecutionSummaryForThing();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobId")) {
                jobExecutionSummaryForThing.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobExecutionSummary")) {
                jobExecutionSummaryForThing.setJobExecutionSummary(JobExecutionSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecutionSummaryForThing;
    }

    public static JobExecutionSummaryForThingJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForThingJsonUnmarshaller();
        }
        return instance;
    }
}
