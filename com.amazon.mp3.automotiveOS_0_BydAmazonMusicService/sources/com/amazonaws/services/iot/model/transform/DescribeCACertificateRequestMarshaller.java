package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeCACertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCACertificateRequestMarshaller implements Marshaller<Request<DescribeCACertificateRequest>, DescribeCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeCACertificateRequest> marshall(DescribeCACertificateRequest describeCACertificateRequest) {
        if (describeCACertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeCACertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeCACertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/cacertificate/{caCertificateId}".replace("{caCertificateId}", describeCACertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(describeCACertificateRequest.getCertificateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
