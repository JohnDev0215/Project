package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTargetsForPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForPolicyRequestMarshaller implements Marshaller<Request<ListTargetsForPolicyRequest>, ListTargetsForPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTargetsForPolicyRequest> marshall(ListTargetsForPolicyRequest listTargetsForPolicyRequest) {
        if (listTargetsForPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListTargetsForPolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listTargetsForPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/policy-targets/{policyName}".replace("{policyName}", listTargetsForPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(listTargetsForPolicyRequest.getPolicyName()));
        if (listTargetsForPolicyRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listTargetsForPolicyRequest.getMarker()));
        }
        if (listTargetsForPolicyRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listTargetsForPolicyRequest.getPageSize()));
        }
        defaultRequest.setResourcePath(strReplace);
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
