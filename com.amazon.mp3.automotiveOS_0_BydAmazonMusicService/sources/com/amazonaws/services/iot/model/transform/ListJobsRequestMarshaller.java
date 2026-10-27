package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobsRequestMarshaller implements Marshaller<Request<ListJobsRequest>, ListJobsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobsRequest> marshall(ListJobsRequest listJobsRequest) {
        if (listJobsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListJobsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listJobsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listJobsRequest.getStatus() != null) {
            defaultRequest.addParameter(NotificationCompat.CATEGORY_STATUS, StringUtils.fromString(listJobsRequest.getStatus()));
        }
        if (listJobsRequest.getTargetSelection() != null) {
            defaultRequest.addParameter("targetSelection", StringUtils.fromString(listJobsRequest.getTargetSelection()));
        }
        if (listJobsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobsRequest.getMaxResults()));
        }
        if (listJobsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobsRequest.getNextToken()));
        }
        if (listJobsRequest.getThingGroupName() != null) {
            defaultRequest.addParameter("thingGroupName", StringUtils.fromString(listJobsRequest.getThingGroupName()));
        }
        if (listJobsRequest.getThingGroupId() != null) {
            defaultRequest.addParameter("thingGroupId", StringUtils.fromString(listJobsRequest.getThingGroupId()));
        }
        if (listJobsRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(listJobsRequest.getNamespaceId()));
        }
        defaultRequest.setResourcePath("/jobs");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
