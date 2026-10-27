package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteJobTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobTemplateRequestMarshaller implements Marshaller<Request<DeleteJobTemplateRequest>, DeleteJobTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteJobTemplateRequest> marshall(DeleteJobTemplateRequest deleteJobTemplateRequest) {
        if (deleteJobTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteJobTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteJobTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/job-templates/{jobTemplateId}".replace("{jobTemplateId}", deleteJobTemplateRequest.getJobTemplateId() == null ? "" : StringUtils.fromString(deleteJobTemplateRequest.getJobTemplateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
