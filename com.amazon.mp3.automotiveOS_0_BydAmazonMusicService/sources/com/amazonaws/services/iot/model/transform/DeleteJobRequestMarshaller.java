package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobRequestMarshaller implements Marshaller<Request<DeleteJobRequest>, DeleteJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteJobRequest> marshall(DeleteJobRequest deleteJobRequest) {
        if (deleteJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/jobs/{jobId}".replace("{jobId}", deleteJobRequest.getJobId() == null ? "" : StringUtils.fromString(deleteJobRequest.getJobId()));
        if (deleteJobRequest.getForce() != null) {
            defaultRequest.addParameter("force", StringUtils.fromBoolean(deleteJobRequest.getForce()));
        }
        if (deleteJobRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(deleteJobRequest.getNamespaceId()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
