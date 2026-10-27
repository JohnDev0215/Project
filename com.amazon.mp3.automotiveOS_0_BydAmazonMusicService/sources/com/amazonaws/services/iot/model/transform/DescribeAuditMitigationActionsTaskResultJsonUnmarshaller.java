package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAuditMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<DescribeAuditMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static DescribeAuditMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAuditMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAuditMitigationActionsTaskResult describeAuditMitigationActionsTaskResult = new DescribeAuditMitigationActionsTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskStatus")) {
                describeAuditMitigationActionsTaskResult.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("startTime")) {
                describeAuditMitigationActionsTaskResult.setStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("endTime")) {
                describeAuditMitigationActionsTaskResult.setEndTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStatistics")) {
                describeAuditMitigationActionsTaskResult.setTaskStatistics(new MapUnmarshaller(TaskStatisticsForAuditCheckJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("target")) {
                describeAuditMitigationActionsTaskResult.setTarget(AuditMitigationActionsTaskTargetJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("auditCheckToActionsMapping")) {
                describeAuditMitigationActionsTaskResult.setAuditCheckToActionsMapping(new MapUnmarshaller(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance())).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionsDefinition")) {
                describeAuditMitigationActionsTaskResult.setActionsDefinition(new ListUnmarshaller(MitigationActionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAuditMitigationActionsTaskResult;
    }

    public static DescribeAuditMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAuditMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
