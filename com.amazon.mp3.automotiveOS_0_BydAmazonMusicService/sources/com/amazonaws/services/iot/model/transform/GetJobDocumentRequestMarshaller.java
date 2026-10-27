package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetJobDocumentRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetJobDocumentRequestMarshaller implements Marshaller<Request<GetJobDocumentRequest>, GetJobDocumentRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetJobDocumentRequest> marshall(GetJobDocumentRequest getJobDocumentRequest) {
        if (getJobDocumentRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetJobDocumentRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getJobDocumentRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/jobs/{jobId}/job-document".replace("{jobId}", getJobDocumentRequest.getJobId() == null ? "" : StringUtils.fromString(getJobDocumentRequest.getJobId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
