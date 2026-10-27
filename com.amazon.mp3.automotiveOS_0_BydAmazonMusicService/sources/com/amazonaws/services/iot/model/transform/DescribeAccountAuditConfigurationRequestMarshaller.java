package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAccountAuditConfigurationRequestMarshaller implements Marshaller<Request<DescribeAccountAuditConfigurationRequest>, DescribeAccountAuditConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAccountAuditConfigurationRequest> marshall(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) {
        if (describeAccountAuditConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAccountAuditConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAccountAuditConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/audit/configuration");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
