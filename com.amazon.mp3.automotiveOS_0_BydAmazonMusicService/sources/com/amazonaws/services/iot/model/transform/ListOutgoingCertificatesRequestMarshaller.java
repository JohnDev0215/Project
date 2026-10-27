package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListOutgoingCertificatesRequestMarshaller implements Marshaller<Request<ListOutgoingCertificatesRequest>, ListOutgoingCertificatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListOutgoingCertificatesRequest> marshall(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) {
        if (listOutgoingCertificatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListOutgoingCertificatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listOutgoingCertificatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listOutgoingCertificatesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listOutgoingCertificatesRequest.getPageSize()));
        }
        if (listOutgoingCertificatesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listOutgoingCertificatesRequest.getMarker()));
        }
        if (listOutgoingCertificatesRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listOutgoingCertificatesRequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath("/certificates-out-going");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
