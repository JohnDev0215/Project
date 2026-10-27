package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetPolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyVersionRequestMarshaller implements Marshaller<Request<GetPolicyVersionRequest>, GetPolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetPolicyVersionRequest> marshall(GetPolicyVersionRequest getPolicyVersionRequest) {
        if (getPolicyVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetPolicyVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getPolicyVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", getPolicyVersionRequest.getPolicyName() == null ? "" : StringUtils.fromString(getPolicyVersionRequest.getPolicyName())).replace("{policyVersionId}", getPolicyVersionRequest.getPolicyVersionId() != null ? StringUtils.fromString(getPolicyVersionRequest.getPolicyVersionId()) : ""));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
