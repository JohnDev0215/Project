package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditCheckConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class AuditCheckConfigurationJsonMarshaller {
    private static AuditCheckConfigurationJsonMarshaller instance;

    AuditCheckConfigurationJsonMarshaller() {
    }

    public void marshall(AuditCheckConfiguration auditCheckConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (auditCheckConfiguration.getEnabled() != null) {
            Boolean enabled = auditCheckConfiguration.getEnabled();
            awsJsonWriter.name("enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static AuditCheckConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuditCheckConfigurationJsonMarshaller();
        }
        return instance;
    }
}
