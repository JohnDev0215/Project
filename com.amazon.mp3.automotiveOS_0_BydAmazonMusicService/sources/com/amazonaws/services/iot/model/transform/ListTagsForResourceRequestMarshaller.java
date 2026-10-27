package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTagsForResourceRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListTagsForResourceRequestMarshaller implements Marshaller<Request<ListTagsForResourceRequest>, ListTagsForResourceRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTagsForResourceRequest> marshall(ListTagsForResourceRequest listTagsForResourceRequest) {
        if (listTagsForResourceRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListTagsForResourceRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listTagsForResourceRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listTagsForResourceRequest.getResourceArn() != null) {
            defaultRequest.addParameter("resourceArn", StringUtils.fromString(listTagsForResourceRequest.getResourceArn()));
        }
        if (listTagsForResourceRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listTagsForResourceRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/tags");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
