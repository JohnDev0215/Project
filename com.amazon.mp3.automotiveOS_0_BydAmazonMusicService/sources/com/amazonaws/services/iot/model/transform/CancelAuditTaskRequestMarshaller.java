package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelAuditTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class CancelAuditTaskRequestMarshaller implements Marshaller<Request<CancelAuditTaskRequest>, CancelAuditTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelAuditTaskRequest> marshall(CancelAuditTaskRequest cancelAuditTaskRequest) {
        if (cancelAuditTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelAuditTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelAuditTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/audit/tasks/{taskId}/cancel".replace("{taskId}", cancelAuditTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(cancelAuditTaskRequest.getTaskId())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
