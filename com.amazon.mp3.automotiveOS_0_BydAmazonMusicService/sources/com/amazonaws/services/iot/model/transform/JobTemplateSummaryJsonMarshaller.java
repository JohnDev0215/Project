package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobTemplateSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class JobTemplateSummaryJsonMarshaller {
    private static JobTemplateSummaryJsonMarshaller instance;

    JobTemplateSummaryJsonMarshaller() {
    }

    public void marshall(JobTemplateSummary jobTemplateSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobTemplateSummary.getJobTemplateArn() != null) {
            String jobTemplateArn = jobTemplateSummary.getJobTemplateArn();
            awsJsonWriter.name("jobTemplateArn");
            awsJsonWriter.value(jobTemplateArn);
        }
        if (jobTemplateSummary.getJobTemplateId() != null) {
            String jobTemplateId = jobTemplateSummary.getJobTemplateId();
            awsJsonWriter.name("jobTemplateId");
            awsJsonWriter.value(jobTemplateId);
        }
        if (jobTemplateSummary.getDescription() != null) {
            String description = jobTemplateSummary.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (jobTemplateSummary.getCreatedAt() != null) {
            Date createdAt = jobTemplateSummary.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        awsJsonWriter.endObject();
    }

    public static JobTemplateSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobTemplateSummaryJsonMarshaller();
        }
        return instance;
    }
}
