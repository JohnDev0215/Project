package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskSummaryJsonUnmarshaller implements Unmarshaller<DetectMitigationActionsTaskSummary, JsonUnmarshallerContext> {
    private static DetectMitigationActionsTaskSummaryJsonUnmarshaller instance;

    DetectMitigationActionsTaskSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetectMitigationActionsTaskSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DetectMitigationActionsTaskSummary detectMitigationActionsTaskSummary = new DetectMitigationActionsTaskSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskId")) {
                detectMitigationActionsTaskSummary.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStatus")) {
                detectMitigationActionsTaskSummary.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStartTime")) {
                detectMitigationActionsTaskSummary.setTaskStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskEndTime")) {
                detectMitigationActionsTaskSummary.setTaskEndTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("target")) {
                detectMitigationActionsTaskSummary.setTarget(DetectMitigationActionsTaskTargetJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationEventOccurrenceRange")) {
                detectMitigationActionsTaskSummary.setViolationEventOccurrenceRange(ViolationEventOccurrenceRangeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("onlyActiveViolationsIncluded")) {
                detectMitigationActionsTaskSummary.setOnlyActiveViolationsIncluded(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("suppressedAlertsIncluded")) {
                detectMitigationActionsTaskSummary.setSuppressedAlertsIncluded(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionsDefinition")) {
                detectMitigationActionsTaskSummary.setActionsDefinition(new ListUnmarshaller(MitigationActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStatistics")) {
                detectMitigationActionsTaskSummary.setTaskStatistics(DetectMitigationActionsTaskStatisticsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return detectMitigationActionsTaskSummary;
    }

    public static DetectMitigationActionsTaskSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
