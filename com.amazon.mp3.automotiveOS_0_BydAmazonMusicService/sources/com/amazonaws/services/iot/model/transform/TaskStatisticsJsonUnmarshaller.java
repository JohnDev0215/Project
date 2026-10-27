package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TaskStatistics;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TaskStatisticsJsonUnmarshaller implements Unmarshaller<TaskStatistics, JsonUnmarshallerContext> {
    private static TaskStatisticsJsonUnmarshaller instance;

    TaskStatisticsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TaskStatistics unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TaskStatistics taskStatistics = new TaskStatistics();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("totalChecks")) {
                taskStatistics.setTotalChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("inProgressChecks")) {
                taskStatistics.setInProgressChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("waitingForDataCollectionChecks")) {
                taskStatistics.setWaitingForDataCollectionChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("compliantChecks")) {
                taskStatistics.setCompliantChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nonCompliantChecks")) {
                taskStatistics.setNonCompliantChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("failedChecks")) {
                taskStatistics.setFailedChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("canceledChecks")) {
                taskStatistics.setCanceledChecks(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return taskStatistics;
    }

    public static TaskStatisticsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TaskStatisticsJsonUnmarshaller();
        }
        return instance;
    }
}
