package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteCertificateRequestMarshaller implements Marshaller<Request<DeleteCertificateRequest>, DeleteCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteCertificateRequest> marshall(DeleteCertificateRequest deleteCertificateRequest) {
        if (deleteCertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteCertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteCertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/certificates/{certificateId}".replace("{certificateId}", deleteCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(deleteCertificateRequest.getCertificateId()));
        if (deleteCertificateRequest.getForceDelete() != null) {
            defaultRequest.addParameter("forceDelete", StringUtils.fromBoolean(deleteCertificateRequest.getForceDelete()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
