package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCertificateRequestMarshaller implements Marshaller<Request<DescribeCertificateRequest>, DescribeCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeCertificateRequest> marshall(DescribeCertificateRequest describeCertificateRequest) {
        if (describeCertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeCertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeCertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/certificates/{certificateId}".replace("{certificateId}", describeCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(describeCertificateRequest.getCertificateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
