package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelAuditMitigationActionsTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class CancelAuditMitigationActionsTaskRequestMarshaller implements Marshaller<Request<CancelAuditMitigationActionsTaskRequest>, CancelAuditMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelAuditMitigationActionsTaskRequest> marshall(CancelAuditMitigationActionsTaskRequest cancelAuditMitigationActionsTaskRequest) {
        if (cancelAuditMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelAuditMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelAuditMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/audit/mitigationactions/tasks/{taskId}/cancel".replace("{taskId}", cancelAuditMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(cancelAuditMitigationActionsTaskRequest.getTaskId())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
