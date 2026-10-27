package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobTemplatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobTemplatesRequestMarshaller implements Marshaller<Request<ListJobTemplatesRequest>, ListJobTemplatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobTemplatesRequest> marshall(ListJobTemplatesRequest listJobTemplatesRequest) {
        if (listJobTemplatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListJobTemplatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listJobTemplatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listJobTemplatesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobTemplatesRequest.getMaxResults()));
        }
        if (listJobTemplatesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobTemplatesRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/job-templates");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
