package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeDomainConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDomainConfigurationRequestMarshaller implements Marshaller<Request<DescribeDomainConfigurationRequest>, DescribeDomainConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeDomainConfigurationRequest> marshall(DescribeDomainConfigurationRequest describeDomainConfigurationRequest) {
        if (describeDomainConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeDomainConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeDomainConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/domainConfigurations/{domainConfigurationName}".replace("{domainConfigurationName}", describeDomainConfigurationRequest.getDomainConfigurationName() == null ? "" : StringUtils.fromString(describeDomainConfigurationRequest.getDomainConfigurationName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
