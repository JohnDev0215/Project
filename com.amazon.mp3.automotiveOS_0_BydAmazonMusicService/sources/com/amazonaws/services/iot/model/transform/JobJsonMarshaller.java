package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.Job;
import com.amazonaws.services.iot.model.JobExecutionsRetryConfig;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.JobProcessDetails;
import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class JobJsonMarshaller {
    private static JobJsonMarshaller instance;

    JobJsonMarshaller() {
    }

    public void marshall(Job job, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (job.getJobArn() != null) {
            String jobArn = job.getJobArn();
            awsJsonWriter.name("jobArn");
            awsJsonWriter.value(jobArn);
        }
        if (job.getJobId() != null) {
            String jobId = job.getJobId();
            awsJsonWriter.name("jobId");
            awsJsonWriter.value(jobId);
        }
        if (job.getTargetSelection() != null) {
            String targetSelection = job.getTargetSelection();
            awsJsonWriter.name("targetSelection");
            awsJsonWriter.value(targetSelection);
        }
        if (job.getStatus() != null) {
            String status = job.getStatus();
            awsJsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            awsJsonWriter.value(status);
        }
        if (job.getForceCanceled() != null) {
            Boolean forceCanceled = job.getForceCanceled();
            awsJsonWriter.name("forceCanceled");
            awsJsonWriter.value(forceCanceled.booleanValue());
        }
        if (job.getReasonCode() != null) {
            String reasonCode = job.getReasonCode();
            awsJsonWriter.name("reasonCode");
            awsJsonWriter.value(reasonCode);
        }
        if (job.getComment() != null) {
            String comment = job.getComment();
            awsJsonWriter.name("comment");
            awsJsonWriter.value(comment);
        }
        if (job.getTargets() != null) {
            List<String> targets = job.getTargets();
            awsJsonWriter.name("targets");
            awsJsonWriter.beginArray();
            for (String str : targets) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (job.getDescription() != null) {
            String description = job.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (job.getPresignedUrlConfig() != null) {
            PresignedUrlConfig presignedUrlConfig = job.getPresignedUrlConfig();
            awsJsonWriter.name("presignedUrlConfig");
            PresignedUrlConfigJsonMarshaller.getInstance().marshall(presignedUrlConfig, awsJsonWriter);
        }
        if (job.getJobExecutionsRolloutConfig() != null) {
            JobExecutionsRolloutConfig jobExecutionsRolloutConfig = job.getJobExecutionsRolloutConfig();
            awsJsonWriter.name("jobExecutionsRolloutConfig");
            JobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(jobExecutionsRolloutConfig, awsJsonWriter);
        }
        if (job.getAbortConfig() != null) {
            AbortConfig abortConfig = job.getAbortConfig();
            awsJsonWriter.name("abortConfig");
            AbortConfigJsonMarshaller.getInstance().marshall(abortConfig, awsJsonWriter);
        }
        if (job.getCreatedAt() != null) {
            Date createdAt = job.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (job.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = job.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (job.getCompletedAt() != null) {
            Date completedAt = job.getCompletedAt();
            awsJsonWriter.name("completedAt");
            awsJsonWriter.value(completedAt);
        }
        if (job.getJobProcessDetails() != null) {
            JobProcessDetails jobProcessDetails = job.getJobProcessDetails();
            awsJsonWriter.name("jobProcessDetails");
            JobProcessDetailsJsonMarshaller.getInstance().marshall(jobProcessDetails, awsJsonWriter);
        }
        if (job.getTimeoutConfig() != null) {
            TimeoutConfig timeoutConfig = job.getTimeoutConfig();
            awsJsonWriter.name("timeoutConfig");
            TimeoutConfigJsonMarshaller.getInstance().marshall(timeoutConfig, awsJsonWriter);
        }
        if (job.getNamespaceId() != null) {
            String namespaceId = job.getNamespaceId();
            awsJsonWriter.name("namespaceId");
            awsJsonWriter.value(namespaceId);
        }
        if (job.getJobTemplateArn() != null) {
            String jobTemplateArn = job.getJobTemplateArn();
            awsJsonWriter.name("jobTemplateArn");
            awsJsonWriter.value(jobTemplateArn);
        }
        if (job.getJobExecutionsRetryConfig() != null) {
            JobExecutionsRetryConfig jobExecutionsRetryConfig = job.getJobExecutionsRetryConfig();
            awsJsonWriter.name("jobExecutionsRetryConfig");
            JobExecutionsRetryConfigJsonMarshaller.getInstance().marshall(jobExecutionsRetryConfig, awsJsonWriter);
        }
        if (job.getDocumentParameters() != null) {
            Map<String, String> documentParameters = job.getDocumentParameters();
            awsJsonWriter.name("documentParameters");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : documentParameters.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (job.getIsConcurrent() != null) {
            Boolean isConcurrent = job.getIsConcurrent();
            awsJsonWriter.name("isConcurrent");
            awsJsonWriter.value(isConcurrent.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static JobJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobJsonMarshaller();
        }
        return instance;
    }
}
