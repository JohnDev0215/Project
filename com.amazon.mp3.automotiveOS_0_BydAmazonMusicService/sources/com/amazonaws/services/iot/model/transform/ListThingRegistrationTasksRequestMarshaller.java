package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingRegistrationTasksRequestMarshaller implements Marshaller<Request<ListThingRegistrationTasksRequest>, ListThingRegistrationTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingRegistrationTasksRequest> marshall(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) {
        if (listThingRegistrationTasksRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListThingRegistrationTasksRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listThingRegistrationTasksRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listThingRegistrationTasksRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingRegistrationTasksRequest.getNextToken()));
        }
        if (listThingRegistrationTasksRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingRegistrationTasksRequest.getMaxResults()));
        }
        if (listThingRegistrationTasksRequest.getStatus() != null) {
            defaultRequest.addParameter(NotificationCompat.CATEGORY_STATUS, StringUtils.fromString(listThingRegistrationTasksRequest.getStatus()));
        }
        defaultRequest.setResourcePath("/thing-registration-tasks");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
