package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskStatistics;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskStatisticsJsonMarshaller {
    private static DetectMitigationActionsTaskStatisticsJsonMarshaller instance;

    DetectMitigationActionsTaskStatisticsJsonMarshaller() {
    }

    public void marshall(DetectMitigationActionsTaskStatistics detectMitigationActionsTaskStatistics, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (detectMitigationActionsTaskStatistics.getActionsExecuted() != null) {
            Long actionsExecuted = detectMitigationActionsTaskStatistics.getActionsExecuted();
            awsJsonWriter.name("actionsExecuted");
            awsJsonWriter.value(actionsExecuted);
        }
        if (detectMitigationActionsTaskStatistics.getActionsSkipped() != null) {
            Long actionsSkipped = detectMitigationActionsTaskStatistics.getActionsSkipped();
            awsJsonWriter.name("actionsSkipped");
            awsJsonWriter.value(actionsSkipped);
        }
        if (detectMitigationActionsTaskStatistics.getActionsFailed() != null) {
            Long actionsFailed = detectMitigationActionsTaskStatistics.getActionsFailed();
            awsJsonWriter.name("actionsFailed");
            awsJsonWriter.value(actionsFailed);
        }
        awsJsonWriter.endObject();
    }

    public static DetectMitigationActionsTaskStatisticsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskStatisticsJsonMarshaller();
        }
        return instance;
    }
}
