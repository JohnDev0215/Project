package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.services.iot.model.ProvisioningTemplateSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class ProvisioningTemplateSummaryJsonMarshaller {
    private static ProvisioningTemplateSummaryJsonMarshaller instance;

    ProvisioningTemplateSummaryJsonMarshaller() {
    }

    public void marshall(ProvisioningTemplateSummary provisioningTemplateSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (provisioningTemplateSummary.getTemplateArn() != null) {
            String templateArn = provisioningTemplateSummary.getTemplateArn();
            awsJsonWriter.name("templateArn");
            awsJsonWriter.value(templateArn);
        }
        if (provisioningTemplateSummary.getTemplateName() != null) {
            String templateName = provisioningTemplateSummary.getTemplateName();
            awsJsonWriter.name("templateName");
            awsJsonWriter.value(templateName);
        }
        if (provisioningTemplateSummary.getDescription() != null) {
            String description = provisioningTemplateSummary.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (provisioningTemplateSummary.getCreationDate() != null) {
            Date creationDate = provisioningTemplateSummary.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (provisioningTemplateSummary.getLastModifiedDate() != null) {
            Date lastModifiedDate = provisioningTemplateSummary.getLastModifiedDate();
            awsJsonWriter.name("lastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (provisioningTemplateSummary.getEnabled() != null) {
            Boolean enabled = provisioningTemplateSummary.getEnabled();
            awsJsonWriter.name("enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        if (provisioningTemplateSummary.getType() != null) {
            String type = provisioningTemplateSummary.getType();
            awsJsonWriter.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            awsJsonWriter.value(type);
        }
        awsJsonWriter.endObject();
    }

    public static ProvisioningTemplateSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisioningTemplateSummaryJsonMarshaller();
        }
        return instance;
    }
}
