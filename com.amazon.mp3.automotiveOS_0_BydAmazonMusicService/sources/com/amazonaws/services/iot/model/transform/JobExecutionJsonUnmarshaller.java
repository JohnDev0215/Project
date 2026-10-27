package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.JobExecution;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionJsonUnmarshaller implements Unmarshaller<JobExecution, JsonUnmarshallerContext> {
    private static JobExecutionJsonUnmarshaller instance;

    JobExecutionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecution unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecution jobExecution = new JobExecution();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobId")) {
                jobExecution.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                jobExecution.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("forceCanceled")) {
                jobExecution.setForceCanceled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("statusDetails")) {
                jobExecution.setStatusDetails(JobExecutionStatusDetailsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingArn")) {
                jobExecution.setThingArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queuedAt")) {
                jobExecution.setQueuedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("startedAt")) {
                jobExecution.setStartedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastUpdatedAt")) {
                jobExecution.setLastUpdatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("executionNumber")) {
                jobExecution.setExecutionNumber(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("versionNumber")) {
                jobExecution.setVersionNumber(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("approximateSecondsBeforeTimedOut")) {
                jobExecution.setApproximateSecondsBeforeTimedOut(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecution;
    }

    public static JobExecutionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionJsonUnmarshaller();
        }
        return instance;
    }
}
