package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingTypeRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingTypeRequestMarshaller implements Marshaller<Request<DeleteThingTypeRequest>, DeleteThingTypeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingTypeRequest> marshall(DeleteThingTypeRequest deleteThingTypeRequest) {
        if (deleteThingTypeRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingTypeRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteThingTypeRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/thing-types/{thingTypeName}".replace("{thingTypeName}", deleteThingTypeRequest.getThingTypeName() == null ? "" : StringUtils.fromString(deleteThingTypeRequest.getThingTypeName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
