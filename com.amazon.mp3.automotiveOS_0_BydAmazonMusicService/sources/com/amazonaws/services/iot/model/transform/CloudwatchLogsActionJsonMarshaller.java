package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CloudwatchLogsAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class CloudwatchLogsActionJsonMarshaller {
    private static CloudwatchLogsActionJsonMarshaller instance;

    CloudwatchLogsActionJsonMarshaller() {
    }

    public void marshall(CloudwatchLogsAction cloudwatchLogsAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (cloudwatchLogsAction.getRoleArn() != null) {
            String roleArn = cloudwatchLogsAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (cloudwatchLogsAction.getLogGroupName() != null) {
            String logGroupName = cloudwatchLogsAction.getLogGroupName();
            awsJsonWriter.name("logGroupName");
            awsJsonWriter.value(logGroupName);
        }
        awsJsonWriter.endObject();
    }

    public static CloudwatchLogsActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CloudwatchLogsActionJsonMarshaller();
        }
        return instance;
    }
}
