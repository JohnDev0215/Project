package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.services.iot.model.LogTargetConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class LogTargetConfigurationJsonMarshaller {
    private static LogTargetConfigurationJsonMarshaller instance;

    LogTargetConfigurationJsonMarshaller() {
    }

    public void marshall(LogTargetConfiguration logTargetConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (logTargetConfiguration.getLogTarget() != null) {
            LogTarget logTarget = logTargetConfiguration.getLogTarget();
            awsJsonWriter.name("logTarget");
            LogTargetJsonMarshaller.getInstance().marshall(logTarget, awsJsonWriter);
        }
        if (logTargetConfiguration.getLogLevel() != null) {
            String logLevel = logTargetConfiguration.getLogLevel();
            awsJsonWriter.name("logLevel");
            awsJsonWriter.value(logLevel);
        }
        awsJsonWriter.endObject();
    }

    public static LogTargetConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetConfigurationJsonMarshaller();
        }
        return instance;
    }
}
