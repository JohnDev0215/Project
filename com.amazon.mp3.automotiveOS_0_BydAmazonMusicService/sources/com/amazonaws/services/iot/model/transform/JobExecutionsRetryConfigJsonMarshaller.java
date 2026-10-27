package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionsRetryConfig;
import com.amazonaws.services.iot.model.RetryCriteria;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionsRetryConfigJsonMarshaller {
    private static JobExecutionsRetryConfigJsonMarshaller instance;

    JobExecutionsRetryConfigJsonMarshaller() {
    }

    public void marshall(JobExecutionsRetryConfig jobExecutionsRetryConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionsRetryConfig.getCriteriaList() != null) {
            List<RetryCriteria> criteriaList = jobExecutionsRetryConfig.getCriteriaList();
            awsJsonWriter.name("criteriaList");
            awsJsonWriter.beginArray();
            for (RetryCriteria retryCriteria : criteriaList) {
                if (retryCriteria != null) {
                    RetryCriteriaJsonMarshaller.getInstance().marshall(retryCriteria, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static JobExecutionsRetryConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionsRetryConfigJsonMarshaller();
        }
        return instance;
    }
}
