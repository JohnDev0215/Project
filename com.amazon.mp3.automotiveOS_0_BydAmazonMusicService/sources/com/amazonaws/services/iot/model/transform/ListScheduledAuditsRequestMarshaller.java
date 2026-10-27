package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListScheduledAuditsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListScheduledAuditsRequestMarshaller implements Marshaller<Request<ListScheduledAuditsRequest>, ListScheduledAuditsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListScheduledAuditsRequest> marshall(ListScheduledAuditsRequest listScheduledAuditsRequest) {
        if (listScheduledAuditsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListScheduledAuditsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listScheduledAuditsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listScheduledAuditsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listScheduledAuditsRequest.getNextToken()));
        }
        if (listScheduledAuditsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listScheduledAuditsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/audit/scheduledaudits");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
