package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAccountAuditConfigurationRequestMarshaller implements Marshaller<Request<DeleteAccountAuditConfigurationRequest>, DeleteAccountAuditConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteAccountAuditConfigurationRequest> marshall(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) {
        if (deleteAccountAuditConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteAccountAuditConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteAccountAuditConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        if (deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() != null) {
            defaultRequest.addParameter("deleteScheduledAudits", StringUtils.fromBoolean(deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits()));
        }
        defaultRequest.setResourcePath("/audit/configuration");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
