package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ReplaceDefaultPolicyVersionParams;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ReplaceDefaultPolicyVersionParamsJsonMarshaller {
    private static ReplaceDefaultPolicyVersionParamsJsonMarshaller instance;

    ReplaceDefaultPolicyVersionParamsJsonMarshaller() {
    }

    public void marshall(ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (replaceDefaultPolicyVersionParams.getTemplateName() != null) {
            String templateName = replaceDefaultPolicyVersionParams.getTemplateName();
            awsJsonWriter.name("templateName");
            awsJsonWriter.value(templateName);
        }
        awsJsonWriter.endObject();
    }

    public static ReplaceDefaultPolicyVersionParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ReplaceDefaultPolicyVersionParamsJsonMarshaller();
        }
        return instance;
    }
}
