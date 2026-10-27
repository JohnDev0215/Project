package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsExecutionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListDetectMitigationActionsExecutionsRequestMarshaller implements Marshaller<Request<ListDetectMitigationActionsExecutionsRequest>, ListDetectMitigationActionsExecutionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListDetectMitigationActionsExecutionsRequest> marshall(ListDetectMitigationActionsExecutionsRequest listDetectMitigationActionsExecutionsRequest) {
        if (listDetectMitigationActionsExecutionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListDetectMitigationActionsExecutionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listDetectMitigationActionsExecutionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listDetectMitigationActionsExecutionsRequest.getTaskId() != null) {
            defaultRequest.addParameter("taskId", StringUtils.fromString(listDetectMitigationActionsExecutionsRequest.getTaskId()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getViolationId() != null) {
            defaultRequest.addParameter("violationId", StringUtils.fromString(listDetectMitigationActionsExecutionsRequest.getViolationId()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getThingName() != null) {
            defaultRequest.addParameter("thingName", StringUtils.fromString(listDetectMitigationActionsExecutionsRequest.getThingName()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getStartTime() != null) {
            defaultRequest.addParameter("startTime", StringUtils.fromDate(listDetectMitigationActionsExecutionsRequest.getStartTime()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getEndTime() != null) {
            defaultRequest.addParameter("endTime", StringUtils.fromDate(listDetectMitigationActionsExecutionsRequest.getEndTime()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listDetectMitigationActionsExecutionsRequest.getMaxResults()));
        }
        if (listDetectMitigationActionsExecutionsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listDetectMitigationActionsExecutionsRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/detect/mitigationactions/executions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
