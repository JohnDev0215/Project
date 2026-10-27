package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPolicyVersionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyVersionsRequestMarshaller implements Marshaller<Request<ListPolicyVersionsRequest>, ListPolicyVersionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPolicyVersionsRequest> marshall(ListPolicyVersionsRequest listPolicyVersionsRequest) {
        if (listPolicyVersionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListPolicyVersionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listPolicyVersionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/policies/{policyName}/version".replace("{policyName}", listPolicyVersionsRequest.getPolicyName() == null ? "" : StringUtils.fromString(listPolicyVersionsRequest.getPolicyName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
