package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsTasksRequestMarshaller implements Marshaller<Request<ListAuditMitigationActionsTasksRequest>, ListAuditMitigationActionsTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuditMitigationActionsTasksRequest> marshall(ListAuditMitigationActionsTasksRequest listAuditMitigationActionsTasksRequest) {
        if (listAuditMitigationActionsTasksRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListAuditMitigationActionsTasksRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listAuditMitigationActionsTasksRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listAuditMitigationActionsTasksRequest.getAuditTaskId() != null) {
            defaultRequest.addParameter("auditTaskId", StringUtils.fromString(listAuditMitigationActionsTasksRequest.getAuditTaskId()));
        }
        if (listAuditMitigationActionsTasksRequest.getFindingId() != null) {
            defaultRequest.addParameter("findingId", StringUtils.fromString(listAuditMitigationActionsTasksRequest.getFindingId()));
        }
        if (listAuditMitigationActionsTasksRequest.getTaskStatus() != null) {
            defaultRequest.addParameter("taskStatus", StringUtils.fromString(listAuditMitigationActionsTasksRequest.getTaskStatus()));
        }
        if (listAuditMitigationActionsTasksRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listAuditMitigationActionsTasksRequest.getMaxResults()));
        }
        if (listAuditMitigationActionsTasksRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listAuditMitigationActionsTasksRequest.getNextToken()));
        }
        if (listAuditMitigationActionsTasksRequest.getStartTime() != null) {
            defaultRequest.addParameter("startTime", StringUtils.fromDate(listAuditMitigationActionsTasksRequest.getStartTime()));
        }
        if (listAuditMitigationActionsTasksRequest.getEndTime() != null) {
            defaultRequest.addParameter("endTime", StringUtils.fromDate(listAuditMitigationActionsTasksRequest.getEndTime()));
        }
        defaultRequest.setResourcePath("/audit/mitigationactions/tasks");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
