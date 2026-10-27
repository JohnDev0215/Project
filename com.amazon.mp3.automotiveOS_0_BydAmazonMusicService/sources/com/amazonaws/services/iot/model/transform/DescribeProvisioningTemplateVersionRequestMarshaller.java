package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateVersionRequestMarshaller implements Marshaller<Request<DescribeProvisioningTemplateVersionRequest>, DescribeProvisioningTemplateVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeProvisioningTemplateVersionRequest> marshall(DescribeProvisioningTemplateVersionRequest describeProvisioningTemplateVersionRequest) {
        if (describeProvisioningTemplateVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeProvisioningTemplateVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeProvisioningTemplateVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}/versions/{versionId}".replace("{templateName}", describeProvisioningTemplateVersionRequest.getTemplateName() == null ? "" : StringUtils.fromString(describeProvisioningTemplateVersionRequest.getTemplateName())).replace("{versionId}", describeProvisioningTemplateVersionRequest.getVersionId() != null ? StringUtils.fromInteger(describeProvisioningTemplateVersionRequest.getVersionId()) : ""));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
