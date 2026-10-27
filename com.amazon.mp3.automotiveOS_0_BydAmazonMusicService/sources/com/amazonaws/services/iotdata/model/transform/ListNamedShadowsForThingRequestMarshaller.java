package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.ListNamedShadowsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListNamedShadowsForThingRequestMarshaller implements Marshaller<Request<ListNamedShadowsForThingRequest>, ListNamedShadowsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListNamedShadowsForThingRequest> marshall(ListNamedShadowsForThingRequest listNamedShadowsForThingRequest) {
        if (listNamedShadowsForThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListNamedShadowsForThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listNamedShadowsForThingRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/api/things/shadow/ListNamedShadowsForThing/{thingName}".replace("{thingName}", listNamedShadowsForThingRequest.getThingName() == null ? "" : StringUtils.fromString(listNamedShadowsForThingRequest.getThingName()));
        if (listNamedShadowsForThingRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listNamedShadowsForThingRequest.getNextToken()));
        }
        if (listNamedShadowsForThingRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listNamedShadowsForThingRequest.getPageSize()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
