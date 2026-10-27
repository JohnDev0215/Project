package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class ClearDefaultAuthorizerRequestMarshaller implements Marshaller<Request<ClearDefaultAuthorizerRequest>, ClearDefaultAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ClearDefaultAuthorizerRequest> marshall(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) {
        if (clearDefaultAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ClearDefaultAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(clearDefaultAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/default-authorizer");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
