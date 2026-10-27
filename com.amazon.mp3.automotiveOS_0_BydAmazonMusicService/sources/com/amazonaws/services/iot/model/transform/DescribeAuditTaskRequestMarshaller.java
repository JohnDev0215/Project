package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuditTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditTaskRequestMarshaller implements Marshaller<Request<DescribeAuditTaskRequest>, DescribeAuditTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuditTaskRequest> marshall(DescribeAuditTaskRequest describeAuditTaskRequest) {
        if (describeAuditTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuditTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAuditTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/audit/tasks/{taskId}".replace("{taskId}", describeAuditTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeAuditTaskRequest.getTaskId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
