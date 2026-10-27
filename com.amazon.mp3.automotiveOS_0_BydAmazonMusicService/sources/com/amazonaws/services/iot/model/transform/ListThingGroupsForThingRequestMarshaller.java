package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingGroupsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsForThingRequestMarshaller implements Marshaller<Request<ListThingGroupsForThingRequest>, ListThingGroupsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingGroupsForThingRequest> marshall(ListThingGroupsForThingRequest listThingGroupsForThingRequest) {
        if (listThingGroupsForThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListThingGroupsForThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listThingGroupsForThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/things/{thingName}/thing-groups".replace("{thingName}", listThingGroupsForThingRequest.getThingName() == null ? "" : StringUtils.fromString(listThingGroupsForThingRequest.getThingName()));
        if (listThingGroupsForThingRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingGroupsForThingRequest.getNextToken()));
        }
        if (listThingGroupsForThingRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingGroupsForThingRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
