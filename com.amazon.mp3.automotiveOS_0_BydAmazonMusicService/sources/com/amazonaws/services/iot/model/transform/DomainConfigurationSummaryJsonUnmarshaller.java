package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DomainConfigurationSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DomainConfigurationSummaryJsonUnmarshaller implements Unmarshaller<DomainConfigurationSummary, JsonUnmarshallerContext> {
    private static DomainConfigurationSummaryJsonUnmarshaller instance;

    DomainConfigurationSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DomainConfigurationSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DomainConfigurationSummary domainConfigurationSummary = new DomainConfigurationSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("domainConfigurationName")) {
                domainConfigurationSummary.setDomainConfigurationName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainConfigurationArn")) {
                domainConfigurationSummary.setDomainConfigurationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serviceType")) {
                domainConfigurationSummary.setServiceType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return domainConfigurationSummary;
    }

    public static DomainConfigurationSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DomainConfigurationSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
