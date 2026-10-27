package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelDetectMitigationActionsTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class CancelDetectMitigationActionsTaskRequestMarshaller implements Marshaller<Request<CancelDetectMitigationActionsTaskRequest>, CancelDetectMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelDetectMitigationActionsTaskRequest> marshall(CancelDetectMitigationActionsTaskRequest cancelDetectMitigationActionsTaskRequest) {
        if (cancelDetectMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelDetectMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelDetectMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/detect/mitigationactions/tasks/{taskId}/cancel".replace("{taskId}", cancelDetectMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(cancelDetectMitigationActionsTaskRequest.getTaskId())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
