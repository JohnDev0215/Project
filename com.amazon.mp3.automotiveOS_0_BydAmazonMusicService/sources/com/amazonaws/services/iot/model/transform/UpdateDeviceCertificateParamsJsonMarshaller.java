package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateDeviceCertificateParams;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class UpdateDeviceCertificateParamsJsonMarshaller {
    private static UpdateDeviceCertificateParamsJsonMarshaller instance;

    UpdateDeviceCertificateParamsJsonMarshaller() {
    }

    public void marshall(UpdateDeviceCertificateParams updateDeviceCertificateParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (updateDeviceCertificateParams.getAction() != null) {
            String action = updateDeviceCertificateParams.getAction();
            awsJsonWriter.name("action");
            awsJsonWriter.value(action);
        }
        awsJsonWriter.endObject();
    }

    public static UpdateDeviceCertificateParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateDeviceCertificateParamsJsonMarshaller();
        }
        return instance;
    }
}
