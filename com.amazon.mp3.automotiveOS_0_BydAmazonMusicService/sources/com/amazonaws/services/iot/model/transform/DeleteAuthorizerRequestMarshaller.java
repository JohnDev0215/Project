package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAuthorizerRequestMarshaller implements Marshaller<Request<DeleteAuthorizerRequest>, DeleteAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteAuthorizerRequest> marshall(DeleteAuthorizerRequest deleteAuthorizerRequest) {
        if (deleteAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", deleteAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(deleteAuthorizerRequest.getAuthorizerName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
