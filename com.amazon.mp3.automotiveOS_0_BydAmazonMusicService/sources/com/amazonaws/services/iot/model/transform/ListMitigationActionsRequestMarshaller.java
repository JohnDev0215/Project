package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListMitigationActionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListMitigationActionsRequestMarshaller implements Marshaller<Request<ListMitigationActionsRequest>, ListMitigationActionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListMitigationActionsRequest> marshall(ListMitigationActionsRequest listMitigationActionsRequest) {
        if (listMitigationActionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListMitigationActionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listMitigationActionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listMitigationActionsRequest.getActionType() != null) {
            defaultRequest.addParameter("actionType", StringUtils.fromString(listMitigationActionsRequest.getActionType()));
        }
        if (listMitigationActionsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listMitigationActionsRequest.getMaxResults()));
        }
        if (listMitigationActionsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listMitigationActionsRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/mitigationactions/actions");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
