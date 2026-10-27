package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCertificatesByCARequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListCertificatesByCARequestMarshaller implements Marshaller<Request<ListCertificatesByCARequest>, ListCertificatesByCARequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCertificatesByCARequest> marshall(ListCertificatesByCARequest listCertificatesByCARequest) {
        if (listCertificatesByCARequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListCertificatesByCARequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listCertificatesByCARequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/certificates-by-ca/{caCertificateId}".replace("{caCertificateId}", listCertificatesByCARequest.getCaCertificateId() == null ? "" : StringUtils.fromString(listCertificatesByCARequest.getCaCertificateId()));
        if (listCertificatesByCARequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listCertificatesByCARequest.getPageSize()));
        }
        if (listCertificatesByCARequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listCertificatesByCARequest.getMarker()));
        }
        if (listCertificatesByCARequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listCertificatesByCARequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
