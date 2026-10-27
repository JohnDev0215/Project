package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEventConfigurationsRequestMarshaller implements Marshaller<Request<DescribeEventConfigurationsRequest>, DescribeEventConfigurationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeEventConfigurationsRequest> marshall(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) {
        if (describeEventConfigurationsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeEventConfigurationsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeEventConfigurationsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/event-configurations");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
