package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteV2LoggingLevelRequestMarshaller implements Marshaller<Request<DeleteV2LoggingLevelRequest>, DeleteV2LoggingLevelRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteV2LoggingLevelRequest> marshall(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) {
        if (deleteV2LoggingLevelRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteV2LoggingLevelRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteV2LoggingLevelRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        if (deleteV2LoggingLevelRequest.getTargetType() != null) {
            defaultRequest.addParameter("targetType", StringUtils.fromString(deleteV2LoggingLevelRequest.getTargetType()));
        }
        if (deleteV2LoggingLevelRequest.getTargetName() != null) {
            defaultRequest.addParameter("targetName", StringUtils.fromString(deleteV2LoggingLevelRequest.getTargetName()));
        }
        defaultRequest.setResourcePath("/v2LoggingLevel");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
