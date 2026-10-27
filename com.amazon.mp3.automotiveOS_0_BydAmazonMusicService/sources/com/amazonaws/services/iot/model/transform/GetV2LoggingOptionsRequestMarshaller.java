package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class GetV2LoggingOptionsRequestMarshaller implements Marshaller<Request<GetV2LoggingOptionsRequest>, GetV2LoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetV2LoggingOptionsRequest> marshall(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) {
        if (getV2LoggingOptionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetV2LoggingOptionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getV2LoggingOptionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/v2LoggingOptions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
