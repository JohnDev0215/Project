package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DomainConfigurationSummary;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class DomainConfigurationSummaryJsonMarshaller {
    private static DomainConfigurationSummaryJsonMarshaller instance;

    DomainConfigurationSummaryJsonMarshaller() {
    }

    public void marshall(DomainConfigurationSummary domainConfigurationSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (domainConfigurationSummary.getDomainConfigurationName() != null) {
            String domainConfigurationName = domainConfigurationSummary.getDomainConfigurationName();
            awsJsonWriter.name("domainConfigurationName");
            awsJsonWriter.value(domainConfigurationName);
        }
        if (domainConfigurationSummary.getDomainConfigurationArn() != null) {
            String domainConfigurationArn = domainConfigurationSummary.getDomainConfigurationArn();
            awsJsonWriter.name("domainConfigurationArn");
            awsJsonWriter.value(domainConfigurationArn);
        }
        if (domainConfigurationSummary.getServiceType() != null) {
            String serviceType = domainConfigurationSummary.getServiceType();
            awsJsonWriter.name("serviceType");
            awsJsonWriter.value(serviceType);
        }
        awsJsonWriter.endObject();
    }

    public static DomainConfigurationSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DomainConfigurationSummaryJsonMarshaller();
        }
        return instance;
    }
}
