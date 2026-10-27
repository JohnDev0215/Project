package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachThingPrincipalRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DetachThingPrincipalRequestMarshaller implements Marshaller<Request<DetachThingPrincipalRequest>, DetachThingPrincipalRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachThingPrincipalRequest> marshall(DetachThingPrincipalRequest detachThingPrincipalRequest) {
        if (detachThingPrincipalRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DetachThingPrincipalRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(detachThingPrincipalRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        if (detachThingPrincipalRequest.getPrincipal() != null) {
            defaultRequest.addHeader("x-amzn-principal", StringUtils.fromString(detachThingPrincipalRequest.getPrincipal()));
        }
        defaultRequest.setResourcePath("/things/{thingName}/principals".replace("{thingName}", detachThingPrincipalRequest.getThingName() == null ? "" : StringUtils.fromString(detachThingPrincipalRequest.getThingName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
