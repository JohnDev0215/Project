package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListManagedJobTemplatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListManagedJobTemplatesRequestMarshaller implements Marshaller<Request<ListManagedJobTemplatesRequest>, ListManagedJobTemplatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListManagedJobTemplatesRequest> marshall(ListManagedJobTemplatesRequest listManagedJobTemplatesRequest) {
        if (listManagedJobTemplatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListManagedJobTemplatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listManagedJobTemplatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listManagedJobTemplatesRequest.getTemplateName() != null) {
            defaultRequest.addParameter("templateName", StringUtils.fromString(listManagedJobTemplatesRequest.getTemplateName()));
        }
        if (listManagedJobTemplatesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listManagedJobTemplatesRequest.getMaxResults()));
        }
        if (listManagedJobTemplatesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listManagedJobTemplatesRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/managed-job-templates");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
