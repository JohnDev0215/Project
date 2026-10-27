package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListBillingGroupsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListBillingGroupsRequestMarshaller implements Marshaller<Request<ListBillingGroupsRequest>, ListBillingGroupsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListBillingGroupsRequest> marshall(ListBillingGroupsRequest listBillingGroupsRequest) {
        if (listBillingGroupsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListBillingGroupsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listBillingGroupsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listBillingGroupsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listBillingGroupsRequest.getNextToken()));
        }
        if (listBillingGroupsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listBillingGroupsRequest.getMaxResults()));
        }
        if (listBillingGroupsRequest.getNamePrefixFilter() != null) {
            defaultRequest.addParameter("namePrefixFilter", StringUtils.fromString(listBillingGroupsRequest.getNamePrefixFilter()));
        }
        defaultRequest.setResourcePath("/billing-groups");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
