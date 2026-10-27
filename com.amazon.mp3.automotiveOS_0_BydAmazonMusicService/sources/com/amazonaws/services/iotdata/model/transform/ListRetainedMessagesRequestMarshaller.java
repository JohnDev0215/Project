package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.ListRetainedMessagesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListRetainedMessagesRequestMarshaller implements Marshaller<Request<ListRetainedMessagesRequest>, ListRetainedMessagesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListRetainedMessagesRequest> marshall(ListRetainedMessagesRequest listRetainedMessagesRequest) {
        if (listRetainedMessagesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListRetainedMessagesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listRetainedMessagesRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listRetainedMessagesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listRetainedMessagesRequest.getNextToken()));
        }
        if (listRetainedMessagesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listRetainedMessagesRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/retainedMessage");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
