package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummary;
import com.amazonaws.services.iot.model.JobExecutionSummaryForJob;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionSummaryForJobJsonMarshaller {
    private static JobExecutionSummaryForJobJsonMarshaller instance;

    JobExecutionSummaryForJobJsonMarshaller() {
    }

    public void marshall(JobExecutionSummaryForJob jobExecutionSummaryForJob, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionSummaryForJob.getThingArn() != null) {
            String thingArn = jobExecutionSummaryForJob.getThingArn();
            awsJsonWriter.name("thingArn");
            awsJsonWriter.value(thingArn);
        }
        if (jobExecutionSummaryForJob.getJobExecutionSummary() != null) {
            JobExecutionSummary jobExecutionSummary = jobExecutionSummaryForJob.getJobExecutionSummary();
            awsJsonWriter.name("jobExecutionSummary");
            JobExecutionSummaryJsonMarshaller.getInstance().marshall(jobExecutionSummary, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static JobExecutionSummaryForJobJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForJobJsonMarshaller();
        }
        return instance;
    }
}
