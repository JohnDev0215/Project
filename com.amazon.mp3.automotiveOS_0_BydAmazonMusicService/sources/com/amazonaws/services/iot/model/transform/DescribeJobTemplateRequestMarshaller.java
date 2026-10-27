package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeJobTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobTemplateRequestMarshaller implements Marshaller<Request<DescribeJobTemplateRequest>, DescribeJobTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeJobTemplateRequest> marshall(DescribeJobTemplateRequest describeJobTemplateRequest) {
        if (describeJobTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeJobTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeJobTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/job-templates/{jobTemplateId}".replace("{jobTemplateId}", describeJobTemplateRequest.getJobTemplateId() == null ? "" : StringUtils.fromString(describeJobTemplateRequest.getJobTemplateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
