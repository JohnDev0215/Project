package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListOTAUpdatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListOTAUpdatesRequestMarshaller implements Marshaller<Request<ListOTAUpdatesRequest>, ListOTAUpdatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListOTAUpdatesRequest> marshall(ListOTAUpdatesRequest listOTAUpdatesRequest) {
        if (listOTAUpdatesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListOTAUpdatesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listOTAUpdatesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listOTAUpdatesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listOTAUpdatesRequest.getMaxResults()));
        }
        if (listOTAUpdatesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listOTAUpdatesRequest.getNextToken()));
        }
        if (listOTAUpdatesRequest.getOtaUpdateStatus() != null) {
            defaultRequest.addParameter("otaUpdateStatus", StringUtils.fromString(listOTAUpdatesRequest.getOtaUpdateStatus()));
        }
        defaultRequest.setResourcePath("/otaUpdates");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
