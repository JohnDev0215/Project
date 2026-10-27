package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.GetRetainedMessageRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetRetainedMessageRequestMarshaller implements Marshaller<Request<GetRetainedMessageRequest>, GetRetainedMessageRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetRetainedMessageRequest> marshall(GetRetainedMessageRequest getRetainedMessageRequest) {
        if (getRetainedMessageRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetRetainedMessageRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getRetainedMessageRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/retainedMessage/{topic}".replace("{topic}", getRetainedMessageRequest.getTopic() == null ? "" : StringUtils.fromString(getRetainedMessageRequest.getTopic())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
