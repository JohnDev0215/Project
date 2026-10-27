package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ProvisioningTemplateVersionSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class ProvisioningTemplateVersionSummaryJsonMarshaller {
    private static ProvisioningTemplateVersionSummaryJsonMarshaller instance;

    ProvisioningTemplateVersionSummaryJsonMarshaller() {
    }

    public void marshall(ProvisioningTemplateVersionSummary provisioningTemplateVersionSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (provisioningTemplateVersionSummary.getVersionId() != null) {
            Integer versionId = provisioningTemplateVersionSummary.getVersionId();
            awsJsonWriter.name("versionId");
            awsJsonWriter.value(versionId);
        }
        if (provisioningTemplateVersionSummary.getCreationDate() != null) {
            Date creationDate = provisioningTemplateVersionSummary.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (provisioningTemplateVersionSummary.getIsDefaultVersion() != null) {
            Boolean isDefaultVersion = provisioningTemplateVersionSummary.getIsDefaultVersion();
            awsJsonWriter.name("isDefaultVersion");
            awsJsonWriter.value(isDefaultVersion.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static ProvisioningTemplateVersionSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisioningTemplateVersionSummaryJsonMarshaller();
        }
        return instance;
    }
}
