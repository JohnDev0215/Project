package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteStreamRequestMarshaller implements Marshaller<Request<DeleteStreamRequest>, DeleteStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteStreamRequest> marshall(DeleteStreamRequest deleteStreamRequest) {
        if (deleteStreamRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteStreamRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteStreamRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/streams/{streamId}".replace("{streamId}", deleteStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(deleteStreamRequest.getStreamId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
