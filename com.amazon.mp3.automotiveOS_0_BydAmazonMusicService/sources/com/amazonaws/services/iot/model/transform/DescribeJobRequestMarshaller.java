package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobRequestMarshaller implements Marshaller<Request<DescribeJobRequest>, DescribeJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeJobRequest> marshall(DescribeJobRequest describeJobRequest) {
        if (describeJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/jobs/{jobId}".replace("{jobId}", describeJobRequest.getJobId() == null ? "" : StringUtils.fromString(describeJobRequest.getJobId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
