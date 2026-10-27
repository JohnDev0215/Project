package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDefaultAuthorizerRequestMarshaller implements Marshaller<Request<DescribeDefaultAuthorizerRequest>, DescribeDefaultAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeDefaultAuthorizerRequest> marshall(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) {
        if (describeDefaultAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeDefaultAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeDefaultAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/default-authorizer");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
