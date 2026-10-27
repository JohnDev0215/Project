package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsExecutionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditMitigationActionsExecutionsRequestMarshaller implements Marshaller<Request<ListAuditMitigationActionsExecutionsRequest>, ListAuditMitigationActionsExecutionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuditMitigationActionsExecutionsRequest> marshall(ListAuditMitigationActionsExecutionsRequest listAuditMitigationActionsExecutionsRequest) {
        if (listAuditMitigationActionsExecutionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListAuditMitigationActionsExecutionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listAuditMitigationActionsExecutionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listAuditMitigationActionsExecutionsRequest.getTaskId() != null) {
            defaultRequest.addParameter("taskId", StringUtils.fromString(listAuditMitigationActionsExecutionsRequest.getTaskId()));
        }
        if (listAuditMitigationActionsExecutionsRequest.getActionStatus() != null) {
            defaultRequest.addParameter("actionStatus", StringUtils.fromString(listAuditMitigationActionsExecutionsRequest.getActionStatus()));
        }
        if (listAuditMitigationActionsExecutionsRequest.getFindingId() != null) {
            defaultRequest.addParameter("findingId", StringUtils.fromString(listAuditMitigationActionsExecutionsRequest.getFindingId()));
        }
        if (listAuditMitigationActionsExecutionsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listAuditMitigationActionsExecutionsRequest.getMaxResults()));
        }
        if (listAuditMitigationActionsExecutionsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listAuditMitigationActionsExecutionsRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/audit/mitigationactions/executions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
