package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDynamicThingGroupRequestMarshaller implements Marshaller<Request<DeleteDynamicThingGroupRequest>, DeleteDynamicThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteDynamicThingGroupRequest> marshall(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) {
        if (deleteDynamicThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteDynamicThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteDynamicThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/dynamic-thing-groups/{thingGroupName}".replace("{thingGroupName}", deleteDynamicThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(deleteDynamicThingGroupRequest.getThingGroupName()));
        if (deleteDynamicThingGroupRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteDynamicThingGroupRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
