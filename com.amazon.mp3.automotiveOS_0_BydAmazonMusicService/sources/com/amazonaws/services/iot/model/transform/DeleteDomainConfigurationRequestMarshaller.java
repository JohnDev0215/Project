package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteDomainConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDomainConfigurationRequestMarshaller implements Marshaller<Request<DeleteDomainConfigurationRequest>, DeleteDomainConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteDomainConfigurationRequest> marshall(DeleteDomainConfigurationRequest deleteDomainConfigurationRequest) {
        if (deleteDomainConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteDomainConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteDomainConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/domainConfigurations/{domainConfigurationName}".replace("{domainConfigurationName}", deleteDomainConfigurationRequest.getDomainConfigurationName() == null ? "" : StringUtils.fromString(deleteDomainConfigurationRequest.getDomainConfigurationName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
