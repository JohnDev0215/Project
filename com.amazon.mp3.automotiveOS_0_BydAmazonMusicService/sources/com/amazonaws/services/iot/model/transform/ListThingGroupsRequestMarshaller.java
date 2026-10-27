package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingGroupsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsRequestMarshaller implements Marshaller<Request<ListThingGroupsRequest>, ListThingGroupsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingGroupsRequest> marshall(ListThingGroupsRequest listThingGroupsRequest) {
        if (listThingGroupsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListThingGroupsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listThingGroupsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listThingGroupsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingGroupsRequest.getNextToken()));
        }
        if (listThingGroupsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingGroupsRequest.getMaxResults()));
        }
        if (listThingGroupsRequest.getParentGroup() != null) {
            defaultRequest.addParameter("parentGroup", StringUtils.fromString(listThingGroupsRequest.getParentGroup()));
        }
        if (listThingGroupsRequest.getNamePrefixFilter() != null) {
            defaultRequest.addParameter("namePrefixFilter", StringUtils.fromString(listThingGroupsRequest.getNamePrefixFilter()));
        }
        if (listThingGroupsRequest.getRecursive() != null) {
            defaultRequest.addParameter("recursive", StringUtils.fromBoolean(listThingGroupsRequest.getRecursive()));
        }
        defaultRequest.setResourcePath("/thing-groups");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
