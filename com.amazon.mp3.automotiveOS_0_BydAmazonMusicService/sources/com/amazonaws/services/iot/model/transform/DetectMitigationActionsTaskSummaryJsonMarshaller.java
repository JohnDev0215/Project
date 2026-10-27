package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskStatistics;
import com.amazonaws.services.iot.model.DetectMitigationActionsTaskSummary;
import com.amazonaws.services.iot.model.DetectMitigationActionsTaskTarget;
import com.amazonaws.services.iot.model.MitigationAction;
import com.amazonaws.services.iot.model.ViolationEventOccurrenceRange;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskSummaryJsonMarshaller {
    private static DetectMitigationActionsTaskSummaryJsonMarshaller instance;

    DetectMitigationActionsTaskSummaryJsonMarshaller() {
    }

    public void marshall(DetectMitigationActionsTaskSummary detectMitigationActionsTaskSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (detectMitigationActionsTaskSummary.getTaskId() != null) {
            String taskId = detectMitigationActionsTaskSummary.getTaskId();
            awsJsonWriter.name("taskId");
            awsJsonWriter.value(taskId);
        }
        if (detectMitigationActionsTaskSummary.getTaskStatus() != null) {
            String taskStatus = detectMitigationActionsTaskSummary.getTaskStatus();
            awsJsonWriter.name("taskStatus");
            awsJsonWriter.value(taskStatus);
        }
        if (detectMitigationActionsTaskSummary.getTaskStartTime() != null) {
            Date taskStartTime = detectMitigationActionsTaskSummary.getTaskStartTime();
            awsJsonWriter.name("taskStartTime");
            awsJsonWriter.value(taskStartTime);
        }
        if (detectMitigationActionsTaskSummary.getTaskEndTime() != null) {
            Date taskEndTime = detectMitigationActionsTaskSummary.getTaskEndTime();
            awsJsonWriter.name("taskEndTime");
            awsJsonWriter.value(taskEndTime);
        }
        if (detectMitigationActionsTaskSummary.getTarget() != null) {
            DetectMitigationActionsTaskTarget target = detectMitigationActionsTaskSummary.getTarget();
            awsJsonWriter.name("target");
            DetectMitigationActionsTaskTargetJsonMarshaller.getInstance().marshall(target, awsJsonWriter);
        }
        if (detectMitigationActionsTaskSummary.getViolationEventOccurrenceRange() != null) {
            ViolationEventOccurrenceRange violationEventOccurrenceRange = detectMitigationActionsTaskSummary.getViolationEventOccurrenceRange();
            awsJsonWriter.name("violationEventOccurrenceRange");
            ViolationEventOccurrenceRangeJsonMarshaller.getInstance().marshall(violationEventOccurrenceRange, awsJsonWriter);
        }
        if (detectMitigationActionsTaskSummary.getOnlyActiveViolationsIncluded() != null) {
            Boolean onlyActiveViolationsIncluded = detectMitigationActionsTaskSummary.getOnlyActiveViolationsIncluded();
            awsJsonWriter.name("onlyActiveViolationsIncluded");
            awsJsonWriter.value(onlyActiveViolationsIncluded.booleanValue());
        }
        if (detectMitigationActionsTaskSummary.getSuppressedAlertsIncluded() != null) {
            Boolean suppressedAlertsIncluded = detectMitigationActionsTaskSummary.getSuppressedAlertsIncluded();
            awsJsonWriter.name("suppressedAlertsIncluded");
            awsJsonWriter.value(suppressedAlertsIncluded.booleanValue());
        }
        if (detectMitigationActionsTaskSummary.getActionsDefinition() != null) {
            List<MitigationAction> actionsDefinition = detectMitigationActionsTaskSummary.getActionsDefinition();
            awsJsonWriter.name("actionsDefinition");
            awsJsonWriter.beginArray();
            for (MitigationAction mitigationAction : actionsDefinition) {
                if (mitigationAction != null) {
                    MitigationActionJsonMarshaller.getInstance().marshall(mitigationAction, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (detectMitigationActionsTaskSummary.getTaskStatistics() != null) {
            DetectMitigationActionsTaskStatistics taskStatistics = detectMitigationActionsTaskSummary.getTaskStatistics();
            awsJsonWriter.name("taskStatistics");
            DetectMitigationActionsTaskStatisticsJsonMarshaller.getInstance().marshall(taskStatistics, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static DetectMitigationActionsTaskSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskSummaryJsonMarshaller();
        }
        return instance;
    }
}
