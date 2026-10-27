package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Configuration;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ConfigurationJsonMarshaller {
    private static ConfigurationJsonMarshaller instance;

    ConfigurationJsonMarshaller() {
    }

    public void marshall(Configuration configuration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (configuration.getEnabled() != null) {
            Boolean enabled = configuration.getEnabled();
            awsJsonWriter.name("Enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static ConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ConfigurationJsonMarshaller();
        }
        return instance;
    }
}
