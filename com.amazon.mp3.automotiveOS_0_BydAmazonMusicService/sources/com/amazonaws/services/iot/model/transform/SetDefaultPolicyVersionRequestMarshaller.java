package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class SetDefaultPolicyVersionRequestMarshaller implements Marshaller<Request<SetDefaultPolicyVersionRequest>, SetDefaultPolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetDefaultPolicyVersionRequest> marshall(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) {
        if (setDefaultPolicyVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SetDefaultPolicyVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(setDefaultPolicyVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", setDefaultPolicyVersionRequest.getPolicyName() == null ? "" : StringUtils.fromString(setDefaultPolicyVersionRequest.getPolicyName())).replace("{policyVersionId}", setDefaultPolicyVersionRequest.getPolicyVersionId() != null ? StringUtils.fromString(setDefaultPolicyVersionRequest.getPolicyVersionId()) : ""));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
