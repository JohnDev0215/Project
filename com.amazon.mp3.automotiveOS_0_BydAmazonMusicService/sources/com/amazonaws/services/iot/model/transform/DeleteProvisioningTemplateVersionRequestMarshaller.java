package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateVersionRequestMarshaller implements Marshaller<Request<DeleteProvisioningTemplateVersionRequest>, DeleteProvisioningTemplateVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteProvisioningTemplateVersionRequest> marshall(DeleteProvisioningTemplateVersionRequest deleteProvisioningTemplateVersionRequest) {
        if (deleteProvisioningTemplateVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteProvisioningTemplateVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteProvisioningTemplateVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}/versions/{versionId}".replace("{templateName}", deleteProvisioningTemplateVersionRequest.getTemplateName() == null ? "" : StringUtils.fromString(deleteProvisioningTemplateVersionRequest.getTemplateName())).replace("{versionId}", deleteProvisioningTemplateVersionRequest.getVersionId() != null ? StringUtils.fromInteger(deleteProvisioningTemplateVersionRequest.getVersionId()) : ""));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
