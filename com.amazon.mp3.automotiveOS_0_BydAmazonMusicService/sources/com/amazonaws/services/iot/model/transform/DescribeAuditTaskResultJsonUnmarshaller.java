package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAuditTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditTaskResultJsonUnmarshaller implements Unmarshaller<DescribeAuditTaskResult, JsonUnmarshallerContext> {
    private static DescribeAuditTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAuditTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAuditTaskResult describeAuditTaskResult = new DescribeAuditTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskStatus")) {
                describeAuditTaskResult.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskType")) {
                describeAuditTaskResult.setTaskType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStartTime")) {
                describeAuditTaskResult.setTaskStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("taskStatistics")) {
                describeAuditTaskResult.setTaskStatistics(TaskStatisticsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("scheduledAuditName")) {
                describeAuditTaskResult.setScheduledAuditName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("auditDetails")) {
                describeAuditTaskResult.setAuditDetails(new MapUnmarshaller(AuditCheckDetailsJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAuditTaskResult;
    }

    public static DescribeAuditTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAuditTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
