package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelCertificateTransferRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class CancelCertificateTransferRequestMarshaller implements Marshaller<Request<CancelCertificateTransferRequest>, CancelCertificateTransferRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelCertificateTransferRequest> marshall(CancelCertificateTransferRequest cancelCertificateTransferRequest) {
        if (cancelCertificateTransferRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelCertificateTransferRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelCertificateTransferRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/cancel-certificate-transfer/{certificateId}".replace("{certificateId}", cancelCertificateTransferRequest.getCertificateId() == null ? "" : StringUtils.fromString(cancelCertificateTransferRequest.getCertificateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
