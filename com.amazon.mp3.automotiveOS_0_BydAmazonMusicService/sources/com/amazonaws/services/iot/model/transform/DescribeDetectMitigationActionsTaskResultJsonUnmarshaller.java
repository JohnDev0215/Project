package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeDetectMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDetectMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<DescribeDetectMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static DescribeDetectMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeDetectMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeDetectMitigationActionsTaskResult describeDetectMitigationActionsTaskResult = new DescribeDetectMitigationActionsTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskSummary")) {
                describeDetectMitigationActionsTaskResult.setTaskSummary(DetectMitigationActionsTaskSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeDetectMitigationActionsTaskResult;
    }

    public static DescribeDetectMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeDetectMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
