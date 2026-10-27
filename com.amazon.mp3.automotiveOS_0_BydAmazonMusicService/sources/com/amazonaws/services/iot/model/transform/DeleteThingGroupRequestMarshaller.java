package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingGroupRequestMarshaller implements Marshaller<Request<DeleteThingGroupRequest>, DeleteThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingGroupRequest> marshall(DeleteThingGroupRequest deleteThingGroupRequest) {
        if (deleteThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/thing-groups/{thingGroupName}".replace("{thingGroupName}", deleteThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(deleteThingGroupRequest.getThingGroupName()));
        if (deleteThingGroupRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteThingGroupRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
