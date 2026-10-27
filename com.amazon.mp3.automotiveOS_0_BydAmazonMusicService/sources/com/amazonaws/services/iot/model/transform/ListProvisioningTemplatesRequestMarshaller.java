package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListProvisioningTemplatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplatesRequestMarshaller implements Marshaller<Request<ListProvisioningTemplatesRequest>, ListProvisioningTemplatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListProvisioningTemplatesRequest> marshall(ListProvisioningTemplatesRequest listProvisioningTemplatesRequest) {
        if (listProvisioningTemplatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListProvisioningTemplatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listProvisioningTemplatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listProvisioningTemplatesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listProvisioningTemplatesRequest.getMaxResults()));
        }
        if (listProvisioningTemplatesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listProvisioningTemplatesRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/provisioning-templates");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
