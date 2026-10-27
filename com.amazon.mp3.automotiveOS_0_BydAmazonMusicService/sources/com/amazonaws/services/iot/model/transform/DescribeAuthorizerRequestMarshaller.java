package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuthorizerRequestMarshaller implements Marshaller<Request<DescribeAuthorizerRequest>, DescribeAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuthorizerRequest> marshall(DescribeAuthorizerRequest describeAuthorizerRequest) {
        if (describeAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", describeAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(describeAuthorizerRequest.getAuthorizerName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
