package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class AttachPrincipalPolicyRequestMarshaller implements Marshaller<Request<AttachPrincipalPolicyRequest>, AttachPrincipalPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachPrincipalPolicyRequest> marshall(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) {
        if (attachPrincipalPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AttachPrincipalPolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(attachPrincipalPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        if (attachPrincipalPolicyRequest.getPrincipal() != null) {
            defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(attachPrincipalPolicyRequest.getPrincipal()));
        }
        defaultRequest.setResourcePath("/principal-policies/{policyName}".replace("{policyName}", attachPrincipalPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(attachPrincipalPolicyRequest.getPolicyName())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
