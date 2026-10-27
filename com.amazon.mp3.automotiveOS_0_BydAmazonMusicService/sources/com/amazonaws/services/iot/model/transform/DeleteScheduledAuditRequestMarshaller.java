package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteScheduledAuditRequestMarshaller implements Marshaller<Request<DeleteScheduledAuditRequest>, DeleteScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteScheduledAuditRequest> marshall(DeleteScheduledAuditRequest deleteScheduledAuditRequest) {
        if (deleteScheduledAuditRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteScheduledAuditRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteScheduledAuditRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/audit/scheduledaudits/{scheduledAuditName}".replace("{scheduledAuditName}", deleteScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(deleteScheduledAuditRequest.getScheduledAuditName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
