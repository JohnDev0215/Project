package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingPrincipalsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingPrincipalsRequestMarshaller implements Marshaller<Request<ListThingPrincipalsRequest>, ListThingPrincipalsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingPrincipalsRequest> marshall(ListThingPrincipalsRequest listThingPrincipalsRequest) {
        if (listThingPrincipalsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListThingPrincipalsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listThingPrincipalsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listThingPrincipalsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingPrincipalsRequest.getNextToken()));
        }
        if (listThingPrincipalsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingPrincipalsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/things/{thingName}/principals".replace("{thingName}", listThingPrincipalsRequest.getThingName() == null ? "" : StringUtils.fromString(listThingPrincipalsRequest.getThingName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
