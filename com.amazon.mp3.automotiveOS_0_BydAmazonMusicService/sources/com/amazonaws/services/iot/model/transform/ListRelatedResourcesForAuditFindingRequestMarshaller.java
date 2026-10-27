package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListRelatedResourcesForAuditFindingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListRelatedResourcesForAuditFindingRequestMarshaller implements Marshaller<Request<ListRelatedResourcesForAuditFindingRequest>, ListRelatedResourcesForAuditFindingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListRelatedResourcesForAuditFindingRequest> marshall(ListRelatedResourcesForAuditFindingRequest listRelatedResourcesForAuditFindingRequest) {
        if (listRelatedResourcesForAuditFindingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListRelatedResourcesForAuditFindingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listRelatedResourcesForAuditFindingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listRelatedResourcesForAuditFindingRequest.getFindingId() != null) {
            defaultRequest.addParameter("findingId", StringUtils.fromString(listRelatedResourcesForAuditFindingRequest.getFindingId()));
        }
        if (listRelatedResourcesForAuditFindingRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listRelatedResourcesForAuditFindingRequest.getNextToken()));
        }
        if (listRelatedResourcesForAuditFindingRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listRelatedResourcesForAuditFindingRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/audit/relatedResources");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
