package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TaskStatisticsForAuditCheck;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TaskStatisticsForAuditCheckJsonUnmarshaller implements Unmarshaller<TaskStatisticsForAuditCheck, JsonUnmarshallerContext> {
    private static TaskStatisticsForAuditCheckJsonUnmarshaller instance;

    TaskStatisticsForAuditCheckJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TaskStatisticsForAuditCheck unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TaskStatisticsForAuditCheck taskStatisticsForAuditCheck = new TaskStatisticsForAuditCheck();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("totalFindingsCount")) {
                taskStatisticsForAuditCheck.setTotalFindingsCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("failedFindingsCount")) {
                taskStatisticsForAuditCheck.setFailedFindingsCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("succeededFindingsCount")) {
                taskStatisticsForAuditCheck.setSucceededFindingsCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("skippedFindingsCount")) {
                taskStatisticsForAuditCheck.setSkippedFindingsCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("canceledFindingsCount")) {
                taskStatisticsForAuditCheck.setCanceledFindingsCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return taskStatisticsForAuditCheck;
    }

    public static TaskStatisticsForAuditCheckJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TaskStatisticsForAuditCheckJsonUnmarshaller();
        }
        return instance;
    }
}
