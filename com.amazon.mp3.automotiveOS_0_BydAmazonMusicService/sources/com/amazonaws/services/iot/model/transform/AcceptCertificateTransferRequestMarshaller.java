package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AcceptCertificateTransferRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class AcceptCertificateTransferRequestMarshaller implements Marshaller<Request<AcceptCertificateTransferRequest>, AcceptCertificateTransferRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AcceptCertificateTransferRequest> marshall(AcceptCertificateTransferRequest acceptCertificateTransferRequest) {
        if (acceptCertificateTransferRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AcceptCertificateTransferRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(acceptCertificateTransferRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        String strReplace = "/accept-certificate-transfer/{certificateId}".replace("{certificateId}", acceptCertificateTransferRequest.getCertificateId() == null ? "" : StringUtils.fromString(acceptCertificateTransferRequest.getCertificateId()));
        if (acceptCertificateTransferRequest.getSetAsActive() != null) {
            defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(acceptCertificateTransferRequest.getSetAsActive()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
