package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ManagedJobTemplateSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class ManagedJobTemplateSummaryJsonMarshaller {
    private static ManagedJobTemplateSummaryJsonMarshaller instance;

    ManagedJobTemplateSummaryJsonMarshaller() {
    }

    public void marshall(ManagedJobTemplateSummary managedJobTemplateSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (managedJobTemplateSummary.getTemplateArn() != null) {
            String templateArn = managedJobTemplateSummary.getTemplateArn();
            awsJsonWriter.name("templateArn");
            awsJsonWriter.value(templateArn);
        }
        if (managedJobTemplateSummary.getTemplateName() != null) {
            String templateName = managedJobTemplateSummary.getTemplateName();
            awsJsonWriter.name("templateName");
            awsJsonWriter.value(templateName);
        }
        if (managedJobTemplateSummary.getDescription() != null) {
            String description = managedJobTemplateSummary.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (managedJobTemplateSummary.getEnvironments() != null) {
            List<String> environments = managedJobTemplateSummary.getEnvironments();
            awsJsonWriter.name("environments");
            awsJsonWriter.beginArray();
            for (String str : environments) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (managedJobTemplateSummary.getTemplateVersion() != null) {
            String templateVersion = managedJobTemplateSummary.getTemplateVersion();
            awsJsonWriter.name("templateVersion");
            awsJsonWriter.value(templateVersion);
        }
        awsJsonWriter.endObject();
    }

    public static ManagedJobTemplateSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ManagedJobTemplateSummaryJsonMarshaller();
        }
        return instance;
    }
}
