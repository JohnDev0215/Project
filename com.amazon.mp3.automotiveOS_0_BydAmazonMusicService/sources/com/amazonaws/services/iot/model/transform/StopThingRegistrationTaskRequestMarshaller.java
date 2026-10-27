package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class StopThingRegistrationTaskRequestMarshaller implements Marshaller<Request<StopThingRegistrationTaskRequest>, StopThingRegistrationTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StopThingRegistrationTaskRequest> marshall(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) {
        if (stopThingRegistrationTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(StopThingRegistrationTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(stopThingRegistrationTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/thing-registration-tasks/{taskId}/cancel".replace("{taskId}", stopThingRegistrationTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(stopThingRegistrationTaskRequest.getTaskId())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
