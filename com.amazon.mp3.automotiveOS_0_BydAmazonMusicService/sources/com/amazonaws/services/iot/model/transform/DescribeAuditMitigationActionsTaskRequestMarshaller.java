package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuditMitigationActionsTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditMitigationActionsTaskRequestMarshaller implements Marshaller<Request<DescribeAuditMitigationActionsTaskRequest>, DescribeAuditMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuditMitigationActionsTaskRequest> marshall(DescribeAuditMitigationActionsTaskRequest describeAuditMitigationActionsTaskRequest) {
        if (describeAuditMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuditMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAuditMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/audit/mitigationactions/tasks/{taskId}".replace("{taskId}", describeAuditMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeAuditMitigationActionsTaskRequest.getTaskId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
