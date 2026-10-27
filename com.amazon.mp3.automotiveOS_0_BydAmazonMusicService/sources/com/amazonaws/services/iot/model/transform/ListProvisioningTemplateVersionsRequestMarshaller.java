package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListProvisioningTemplateVersionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplateVersionsRequestMarshaller implements Marshaller<Request<ListProvisioningTemplateVersionsRequest>, ListProvisioningTemplateVersionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListProvisioningTemplateVersionsRequest> marshall(ListProvisioningTemplateVersionsRequest listProvisioningTemplateVersionsRequest) {
        if (listProvisioningTemplateVersionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListProvisioningTemplateVersionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listProvisioningTemplateVersionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/provisioning-templates/{templateName}/versions".replace("{templateName}", listProvisioningTemplateVersionsRequest.getTemplateName() == null ? "" : StringUtils.fromString(listProvisioningTemplateVersionsRequest.getTemplateName()));
        if (listProvisioningTemplateVersionsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listProvisioningTemplateVersionsRequest.getMaxResults()));
        }
        if (listProvisioningTemplateVersionsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listProvisioningTemplateVersionsRequest.getNextToken()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
