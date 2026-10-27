package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingRequestMarshaller implements Marshaller<Request<DeleteThingRequest>, DeleteThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingRequest> marshall(DeleteThingRequest deleteThingRequest) {
        if (deleteThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/things/{thingName}".replace("{thingName}", deleteThingRequest.getThingName() == null ? "" : StringUtils.fromString(deleteThingRequest.getThingName()));
        if (deleteThingRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteThingRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
