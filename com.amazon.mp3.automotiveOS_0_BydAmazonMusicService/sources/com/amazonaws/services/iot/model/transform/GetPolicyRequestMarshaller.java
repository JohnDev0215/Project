package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyRequestMarshaller implements Marshaller<Request<GetPolicyRequest>, GetPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetPolicyRequest> marshall(GetPolicyRequest getPolicyRequest) {
        if (getPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetPolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/policies/{policyName}".replace("{policyName}", getPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(getPolicyRequest.getPolicyName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
