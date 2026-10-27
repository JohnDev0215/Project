package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeDetectMitigationActionsTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDetectMitigationActionsTaskRequestMarshaller implements Marshaller<Request<DescribeDetectMitigationActionsTaskRequest>, DescribeDetectMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeDetectMitigationActionsTaskRequest> marshall(DescribeDetectMitigationActionsTaskRequest describeDetectMitigationActionsTaskRequest) {
        if (describeDetectMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeDetectMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeDetectMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/detect/mitigationactions/tasks/{taskId}".replace("{taskId}", describeDetectMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeDetectMitigationActionsTaskRequest.getTaskId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
