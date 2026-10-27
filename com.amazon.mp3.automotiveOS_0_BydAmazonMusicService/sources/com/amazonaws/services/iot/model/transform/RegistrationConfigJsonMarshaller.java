package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class RegistrationConfigJsonMarshaller {
    private static RegistrationConfigJsonMarshaller instance;

    RegistrationConfigJsonMarshaller() {
    }

    public void marshall(RegistrationConfig registrationConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (registrationConfig.getTemplateBody() != null) {
            String templateBody = registrationConfig.getTemplateBody();
            awsJsonWriter.name("templateBody");
            awsJsonWriter.value(templateBody);
        }
        if (registrationConfig.getRoleArn() != null) {
            String roleArn = registrationConfig.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (registrationConfig.getTemplateName() != null) {
            String templateName = registrationConfig.getTemplateName();
            awsJsonWriter.name("templateName");
            awsJsonWriter.value(templateName);
        }
        awsJsonWriter.endObject();
    }

    public static RegistrationConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RegistrationConfigJsonMarshaller();
        }
        return instance;
    }
}
