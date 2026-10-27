package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummaryForJob;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionSummaryForJobJsonUnmarshaller implements Unmarshaller<JobExecutionSummaryForJob, JsonUnmarshallerContext> {
    private static JobExecutionSummaryForJobJsonUnmarshaller instance;

    JobExecutionSummaryForJobJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecutionSummaryForJob unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecutionSummaryForJob jobExecutionSummaryForJob = new JobExecutionSummaryForJob();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingArn")) {
                jobExecutionSummaryForJob.setThingArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobExecutionSummary")) {
                jobExecutionSummaryForJob.setJobExecutionSummary(JobExecutionSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecutionSummaryForJob;
    }

    public static JobExecutionSummaryForJobJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForJobJsonUnmarshaller();
        }
        return instance;
    }
}
