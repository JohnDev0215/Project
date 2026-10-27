package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateRequestMarshaller implements Marshaller<Request<DescribeProvisioningTemplateRequest>, DescribeProvisioningTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeProvisioningTemplateRequest> marshall(DescribeProvisioningTemplateRequest describeProvisioningTemplateRequest) {
        if (describeProvisioningTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeProvisioningTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeProvisioningTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}".replace("{templateName}", describeProvisioningTemplateRequest.getTemplateName() == null ? "" : StringUtils.fromString(describeProvisioningTemplateRequest.getTemplateName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
