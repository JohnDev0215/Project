package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeletePolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeletePolicyRequestMarshaller implements Marshaller<Request<DeletePolicyRequest>, DeletePolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeletePolicyRequest> marshall(DeletePolicyRequest deletePolicyRequest) {
        if (deletePolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeletePolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deletePolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/policies/{policyName}".replace("{policyName}", deletePolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(deletePolicyRequest.getPolicyName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
