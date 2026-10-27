package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateRequestMarshaller implements Marshaller<Request<DeleteProvisioningTemplateRequest>, DeleteProvisioningTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteProvisioningTemplateRequest> marshall(DeleteProvisioningTemplateRequest deleteProvisioningTemplateRequest) {
        if (deleteProvisioningTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteProvisioningTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteProvisioningTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}".replace("{templateName}", deleteProvisioningTemplateRequest.getTemplateName() == null ? "" : StringUtils.fromString(deleteProvisioningTemplateRequest.getTemplateName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
