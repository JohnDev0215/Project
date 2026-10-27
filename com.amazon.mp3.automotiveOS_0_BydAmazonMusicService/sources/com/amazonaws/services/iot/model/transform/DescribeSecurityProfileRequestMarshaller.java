package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeSecurityProfileRequestMarshaller implements Marshaller<Request<DescribeSecurityProfileRequest>, DescribeSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeSecurityProfileRequest> marshall(DescribeSecurityProfileRequest describeSecurityProfileRequest) {
        if (describeSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/security-profiles/{securityProfileName}".replace("{securityProfileName}", describeSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(describeSecurityProfileRequest.getSecurityProfileName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
