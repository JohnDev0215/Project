package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class LogTargetJsonMarshaller {
    private static LogTargetJsonMarshaller instance;

    LogTargetJsonMarshaller() {
    }

    public void marshall(LogTarget logTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (logTarget.getTargetType() != null) {
            String targetType = logTarget.getTargetType();
            awsJsonWriter.name("targetType");
            awsJsonWriter.value(targetType);
        }
        if (logTarget.getTargetName() != null) {
            String targetName = logTarget.getTargetName();
            awsJsonWriter.name("targetName");
            awsJsonWriter.value(targetName);
        }
        awsJsonWriter.endObject();
    }

    public static LogTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetJsonMarshaller();
        }
        return instance;
    }
}
