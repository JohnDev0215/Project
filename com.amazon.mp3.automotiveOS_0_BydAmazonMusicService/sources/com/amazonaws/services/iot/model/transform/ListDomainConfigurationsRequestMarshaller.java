package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListDomainConfigurationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListDomainConfigurationsRequestMarshaller implements Marshaller<Request<ListDomainConfigurationsRequest>, ListDomainConfigurationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListDomainConfigurationsRequest> marshall(ListDomainConfigurationsRequest listDomainConfigurationsRequest) {
        if (listDomainConfigurationsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListDomainConfigurationsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listDomainConfigurationsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listDomainConfigurationsRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listDomainConfigurationsRequest.getMarker()));
        }
        if (listDomainConfigurationsRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listDomainConfigurationsRequest.getPageSize()));
        }
        if (listDomainConfigurationsRequest.getServiceType() != null) {
            defaultRequest.addParameter("serviceType", StringUtils.fromString(listDomainConfigurationsRequest.getServiceType()));
        }
        defaultRequest.setResourcePath("/domainConfigurations");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
