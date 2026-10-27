package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListDimensionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListDimensionsRequestMarshaller implements Marshaller<Request<ListDimensionsRequest>, ListDimensionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListDimensionsRequest> marshall(ListDimensionsRequest listDimensionsRequest) {
        if (listDimensionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListDimensionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listDimensionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listDimensionsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listDimensionsRequest.getNextToken()));
        }
        if (listDimensionsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listDimensionsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/dimensions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
