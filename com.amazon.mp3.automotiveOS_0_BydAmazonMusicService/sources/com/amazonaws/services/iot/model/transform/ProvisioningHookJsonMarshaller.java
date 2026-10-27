package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ProvisioningHook;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ProvisioningHookJsonMarshaller {
    private static ProvisioningHookJsonMarshaller instance;

    ProvisioningHookJsonMarshaller() {
    }

    public void marshall(ProvisioningHook provisioningHook, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (provisioningHook.getPayloadVersion() != null) {
            String payloadVersion = provisioningHook.getPayloadVersion();
            awsJsonWriter.name("payloadVersion");
            awsJsonWriter.value(payloadVersion);
        }
        if (provisioningHook.getTargetArn() != null) {
            String targetArn = provisioningHook.getTargetArn();
            awsJsonWriter.name("targetArn");
            awsJsonWriter.value(targetArn);
        }
        awsJsonWriter.endObject();
    }

    public static ProvisioningHookJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisioningHookJsonMarshaller();
        }
        return instance;
    }
}
