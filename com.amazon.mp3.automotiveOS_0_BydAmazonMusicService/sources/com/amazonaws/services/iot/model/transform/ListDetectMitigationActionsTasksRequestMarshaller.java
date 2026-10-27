package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsTasksRequestMarshaller implements Marshaller<Request<ListDetectMitigationActionsTasksRequest>, ListDetectMitigationActionsTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListDetectMitigationActionsTasksRequest> marshall(ListDetectMitigationActionsTasksRequest listDetectMitigationActionsTasksRequest) {
        if (listDetectMitigationActionsTasksRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListDetectMitigationActionsTasksRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listDetectMitigationActionsTasksRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listDetectMitigationActionsTasksRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listDetectMitigationActionsTasksRequest.getMaxResults()));
        }
        if (listDetectMitigationActionsTasksRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listDetectMitigationActionsTasksRequest.getNextToken()));
        }
        if (listDetectMitigationActionsTasksRequest.getStartTime() != null) {
            defaultRequest.addParameter("startTime", StringUtils.fromDate(listDetectMitigationActionsTasksRequest.getStartTime()));
        }
        if (listDetectMitigationActionsTasksRequest.getEndTime() != null) {
            defaultRequest.addParameter("endTime", StringUtils.fromDate(listDetectMitigationActionsTasksRequest.getEndTime()));
        }
        defaultRequest.setResourcePath("/detect/mitigationactions/tasks");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
