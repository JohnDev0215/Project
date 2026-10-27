package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachThingPrincipalRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class AttachThingPrincipalRequestMarshaller implements Marshaller<Request<AttachThingPrincipalRequest>, AttachThingPrincipalRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachThingPrincipalRequest> marshall(AttachThingPrincipalRequest attachThingPrincipalRequest) {
        if (attachThingPrincipalRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AttachThingPrincipalRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(attachThingPrincipalRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        if (attachThingPrincipalRequest.getPrincipal() != null) {
            defaultRequest.addHeader("x-amzn-principal", StringUtils.fromString(attachThingPrincipalRequest.getPrincipal()));
        }
        defaultRequest.setResourcePath("/things/{thingName}/principals".replace("{thingName}", attachThingPrincipalRequest.getThingName() == null ? "" : StringUtils.fromString(attachThingPrincipalRequest.getThingName())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
