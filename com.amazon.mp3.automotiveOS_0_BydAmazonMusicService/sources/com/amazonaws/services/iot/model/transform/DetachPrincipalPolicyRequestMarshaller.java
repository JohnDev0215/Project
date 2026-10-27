package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DetachPrincipalPolicyRequestMarshaller implements Marshaller<Request<DetachPrincipalPolicyRequest>, DetachPrincipalPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachPrincipalPolicyRequest> marshall(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) {
        if (detachPrincipalPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DetachPrincipalPolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(detachPrincipalPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        if (detachPrincipalPolicyRequest.getPrincipal() != null) {
            defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(detachPrincipalPolicyRequest.getPrincipal()));
        }
        defaultRequest.setResourcePath("/principal-policies/{policyName}".replace("{policyName}", detachPrincipalPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(detachPrincipalPolicyRequest.getPolicyName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
