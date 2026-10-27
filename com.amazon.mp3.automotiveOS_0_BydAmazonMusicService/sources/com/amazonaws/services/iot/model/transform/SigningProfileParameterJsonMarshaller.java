package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SigningProfileParameter;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class SigningProfileParameterJsonMarshaller {
    private static SigningProfileParameterJsonMarshaller instance;

    SigningProfileParameterJsonMarshaller() {
    }

    public void marshall(SigningProfileParameter signingProfileParameter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (signingProfileParameter.getCertificateArn() != null) {
            String certificateArn = signingProfileParameter.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (signingProfileParameter.getPlatform() != null) {
            String platform = signingProfileParameter.getPlatform();
            awsJsonWriter.name("platform");
            awsJsonWriter.value(platform);
        }
        if (signingProfileParameter.getCertificatePathOnDevice() != null) {
            String certificatePathOnDevice = signingProfileParameter.getCertificatePathOnDevice();
            awsJsonWriter.name("certificatePathOnDevice");
            awsJsonWriter.value(certificatePathOnDevice);
        }
        awsJsonWriter.endObject();
    }

    public static SigningProfileParameterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SigningProfileParameterJsonMarshaller();
        }
        return instance;
    }
}
