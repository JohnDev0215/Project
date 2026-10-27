package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeEndpointRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeEndpointRequestMarshaller implements Marshaller<Request<DescribeEndpointRequest>, DescribeEndpointRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeEndpointRequest> marshall(DescribeEndpointRequest describeEndpointRequest) {
        if (describeEndpointRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeEndpointRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeEndpointRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (describeEndpointRequest.getEndpointType() != null) {
            defaultRequest.addParameter("endpointType", StringUtils.fromString(describeEndpointRequest.getEndpointType()));
        }
        defaultRequest.setResourcePath("/endpoint");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
