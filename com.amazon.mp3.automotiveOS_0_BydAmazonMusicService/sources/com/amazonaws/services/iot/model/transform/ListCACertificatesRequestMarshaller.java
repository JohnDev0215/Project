package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCACertificatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListCACertificatesRequestMarshaller implements Marshaller<Request<ListCACertificatesRequest>, ListCACertificatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCACertificatesRequest> marshall(ListCACertificatesRequest listCACertificatesRequest) {
        if (listCACertificatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListCACertificatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listCACertificatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listCACertificatesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listCACertificatesRequest.getPageSize()));
        }
        if (listCACertificatesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listCACertificatesRequest.getMarker()));
        }
        if (listCACertificatesRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listCACertificatesRequest.getAscendingOrder()));
        }
        if (listCACertificatesRequest.getTemplateName() != null) {
            defaultRequest.addParameter("templateName", StringUtils.fromString(listCACertificatesRequest.getTemplateName()));
        }
        defaultRequest.setResourcePath("/cacertificates");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
