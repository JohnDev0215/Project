package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetLoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class GetLoggingOptionsRequestMarshaller implements Marshaller<Request<GetLoggingOptionsRequest>, GetLoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetLoggingOptionsRequest> marshall(GetLoggingOptionsRequest getLoggingOptionsRequest) {
        if (getLoggingOptionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetLoggingOptionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getLoggingOptionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/loggingOptions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
