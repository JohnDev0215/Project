package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListPoliciesRequestMarshaller implements Marshaller<Request<ListPoliciesRequest>, ListPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPoliciesRequest> marshall(ListPoliciesRequest listPoliciesRequest) {
        if (listPoliciesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListPoliciesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listPoliciesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listPoliciesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listPoliciesRequest.getMarker()));
        }
        if (listPoliciesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPoliciesRequest.getPageSize()));
        }
        if (listPoliciesRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPoliciesRequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath("/policies");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
