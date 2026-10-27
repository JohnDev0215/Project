package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyPrincipalsRequestMarshaller implements Marshaller<Request<ListPolicyPrincipalsRequest>, ListPolicyPrincipalsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPolicyPrincipalsRequest> marshall(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) {
        if (listPolicyPrincipalsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListPolicyPrincipalsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listPolicyPrincipalsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listPolicyPrincipalsRequest.getPolicyName() != null) {
            defaultRequest.addHeader("x-amzn-iot-policy", StringUtils.fromString(listPolicyPrincipalsRequest.getPolicyName()));
        }
        if (listPolicyPrincipalsRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listPolicyPrincipalsRequest.getMarker()));
        }
        if (listPolicyPrincipalsRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPolicyPrincipalsRequest.getPageSize()));
        }
        if (listPolicyPrincipalsRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPolicyPrincipalsRequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath("/policy-principals");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
