package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AlertTargetJsonMarshaller {
    private static AlertTargetJsonMarshaller instance;

    AlertTargetJsonMarshaller() {
    }

    public void marshall(AlertTarget alertTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (alertTarget.getAlertTargetArn() != null) {
            String alertTargetArn = alertTarget.getAlertTargetArn();
            awsJsonWriter.name("alertTargetArn");
            awsJsonWriter.value(alertTargetArn);
        }
        if (alertTarget.getRoleArn() != null) {
            String roleArn = alertTarget.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static AlertTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AlertTargetJsonMarshaller();
        }
        return instance;
    }
}
