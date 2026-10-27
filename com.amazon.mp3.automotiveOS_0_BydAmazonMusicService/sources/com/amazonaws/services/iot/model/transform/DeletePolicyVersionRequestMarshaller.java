package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeletePolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeletePolicyVersionRequestMarshaller implements Marshaller<Request<DeletePolicyVersionRequest>, DeletePolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeletePolicyVersionRequest> marshall(DeletePolicyVersionRequest deletePolicyVersionRequest) {
        if (deletePolicyVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeletePolicyVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deletePolicyVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", deletePolicyVersionRequest.getPolicyName() == null ? "" : StringUtils.fromString(deletePolicyVersionRequest.getPolicyName())).replace("{policyVersionId}", deletePolicyVersionRequest.getPolicyVersionId() != null ? StringUtils.fromString(deletePolicyVersionRequest.getPolicyVersionId()) : ""));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
