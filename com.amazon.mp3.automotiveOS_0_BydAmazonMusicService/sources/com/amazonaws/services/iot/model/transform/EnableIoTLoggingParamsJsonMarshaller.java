package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.EnableIoTLoggingParams;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class EnableIoTLoggingParamsJsonMarshaller {
    private static EnableIoTLoggingParamsJsonMarshaller instance;

    EnableIoTLoggingParamsJsonMarshaller() {
    }

    public void marshall(EnableIoTLoggingParams enableIoTLoggingParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (enableIoTLoggingParams.getRoleArnForLogging() != null) {
            String roleArnForLogging = enableIoTLoggingParams.getRoleArnForLogging();
            awsJsonWriter.name("roleArnForLogging");
            awsJsonWriter.value(roleArnForLogging);
        }
        if (enableIoTLoggingParams.getLogLevel() != null) {
            String logLevel = enableIoTLoggingParams.getLogLevel();
            awsJsonWriter.name("logLevel");
            awsJsonWriter.value(logLevel);
        }
        awsJsonWriter.endObject();
    }

    public static EnableIoTLoggingParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EnableIoTLoggingParamsJsonMarshaller();
        }
        return instance;
    }
}
