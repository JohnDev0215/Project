package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCertificateRequestMarshaller implements Marshaller<Request<UpdateCertificateRequest>, UpdateCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateCertificateRequest> marshall(UpdateCertificateRequest updateCertificateRequest) {
        if (updateCertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateCertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateCertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        String strReplace = "/certificates/{certificateId}".replace("{certificateId}", updateCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(updateCertificateRequest.getCertificateId()));
        if (updateCertificateRequest.getNewStatus() != null) {
            defaultRequest.addParameter("newStatus", StringUtils.fromString(updateCertificateRequest.getNewStatus()));
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
