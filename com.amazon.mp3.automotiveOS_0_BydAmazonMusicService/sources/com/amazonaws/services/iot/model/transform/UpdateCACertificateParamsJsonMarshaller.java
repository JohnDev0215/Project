package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateCACertificateParams;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class UpdateCACertificateParamsJsonMarshaller {
    private static UpdateCACertificateParamsJsonMarshaller instance;

    UpdateCACertificateParamsJsonMarshaller() {
    }

    public void marshall(UpdateCACertificateParams updateCACertificateParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (updateCACertificateParams.getAction() != null) {
            String action = updateCACertificateParams.getAction();
            awsJsonWriter.name("action");
            awsJsonWriter.value(action);
        }
        awsJsonWriter.endObject();
    }

    public static UpdateCACertificateParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateCACertificateParamsJsonMarshaller();
        }
        return instance;
    }
}
