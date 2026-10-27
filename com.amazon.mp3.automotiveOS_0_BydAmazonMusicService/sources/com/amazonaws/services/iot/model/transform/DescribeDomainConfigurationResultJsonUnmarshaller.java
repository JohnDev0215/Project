package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeDomainConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDomainConfigurationResultJsonUnmarshaller implements Unmarshaller<DescribeDomainConfigurationResult, JsonUnmarshallerContext> {
    private static DescribeDomainConfigurationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeDomainConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeDomainConfigurationResult describeDomainConfigurationResult = new DescribeDomainConfigurationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("domainConfigurationName")) {
                describeDomainConfigurationResult.setDomainConfigurationName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainConfigurationArn")) {
                describeDomainConfigurationResult.setDomainConfigurationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainName")) {
                describeDomainConfigurationResult.setDomainName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serverCertificates")) {
                describeDomainConfigurationResult.setServerCertificates(new ListUnmarshaller(ServerCertificateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("authorizerConfig")) {
                describeDomainConfigurationResult.setAuthorizerConfig(AuthorizerConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainConfigurationStatus")) {
                describeDomainConfigurationResult.setDomainConfigurationStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serviceType")) {
                describeDomainConfigurationResult.setServiceType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainType")) {
                describeDomainConfigurationResult.setDomainType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastStatusChangeDate")) {
                describeDomainConfigurationResult.setLastStatusChangeDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeDomainConfigurationResult;
    }

    public static DescribeDomainConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeDomainConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }
}
