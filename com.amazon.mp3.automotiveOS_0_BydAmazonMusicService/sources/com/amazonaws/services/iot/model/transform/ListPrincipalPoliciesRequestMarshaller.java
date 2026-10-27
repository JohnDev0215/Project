package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListPrincipalPoliciesRequestMarshaller implements Marshaller<Request<ListPrincipalPoliciesRequest>, ListPrincipalPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPrincipalPoliciesRequest> marshall(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) {
        if (listPrincipalPoliciesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListPrincipalPoliciesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listPrincipalPoliciesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listPrincipalPoliciesRequest.getPrincipal() != null) {
            defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(listPrincipalPoliciesRequest.getPrincipal()));
        }
        if (listPrincipalPoliciesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listPrincipalPoliciesRequest.getMarker()));
        }
        if (listPrincipalPoliciesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPrincipalPoliciesRequest.getPageSize()));
        }
        if (listPrincipalPoliciesRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPrincipalPoliciesRequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath("/principal-policies");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
