package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobExecutionRequestMarshaller implements Marshaller<Request<DeleteJobExecutionRequest>, DeleteJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteJobExecutionRequest> marshall(DeleteJobExecutionRequest deleteJobExecutionRequest) {
        if (deleteJobExecutionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteJobExecutionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteJobExecutionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/things/{thingName}/jobs/{jobId}/executionNumber/{executionNumber}".replace("{jobId}", deleteJobExecutionRequest.getJobId() == null ? "" : StringUtils.fromString(deleteJobExecutionRequest.getJobId())).replace("{thingName}", deleteJobExecutionRequest.getThingName() == null ? "" : StringUtils.fromString(deleteJobExecutionRequest.getThingName())).replace("{executionNumber}", deleteJobExecutionRequest.getExecutionNumber() != null ? StringUtils.fromLong(deleteJobExecutionRequest.getExecutionNumber()) : "");
        if (deleteJobExecutionRequest.getForce() != null) {
            defaultRequest.addParameter("force", StringUtils.fromBoolean(deleteJobExecutionRequest.getForce()));
        }
        if (deleteJobExecutionRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(deleteJobExecutionRequest.getNamespaceId()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
