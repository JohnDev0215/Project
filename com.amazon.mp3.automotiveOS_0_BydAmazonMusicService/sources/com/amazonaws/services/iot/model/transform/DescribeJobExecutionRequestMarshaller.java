package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobExecutionRequestMarshaller implements Marshaller<Request<DescribeJobExecutionRequest>, DescribeJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeJobExecutionRequest> marshall(DescribeJobExecutionRequest describeJobExecutionRequest) {
        if (describeJobExecutionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeJobExecutionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeJobExecutionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/things/{thingName}/jobs/{jobId}".replace("{jobId}", describeJobExecutionRequest.getJobId() == null ? "" : StringUtils.fromString(describeJobExecutionRequest.getJobId())).replace("{thingName}", describeJobExecutionRequest.getThingName() != null ? StringUtils.fromString(describeJobExecutionRequest.getThingName()) : "");
        if (describeJobExecutionRequest.getExecutionNumber() != null) {
            defaultRequest.addParameter("executionNumber", StringUtils.fromLong(describeJobExecutionRequest.getExecutionNumber()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
