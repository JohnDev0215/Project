package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingRegistrationTaskRequestMarshaller implements Marshaller<Request<DescribeThingRegistrationTaskRequest>, DescribeThingRegistrationTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingRegistrationTaskRequest> marshall(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) {
        if (describeThingRegistrationTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingRegistrationTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeThingRegistrationTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/thing-registration-tasks/{taskId}".replace("{taskId}", describeThingRegistrationTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeThingRegistrationTaskRequest.getTaskId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
