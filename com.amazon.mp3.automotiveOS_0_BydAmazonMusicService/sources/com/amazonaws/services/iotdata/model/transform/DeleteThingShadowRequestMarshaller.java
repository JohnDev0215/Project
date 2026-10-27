package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.DeleteThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingShadowRequestMarshaller implements Marshaller<Request<DeleteThingShadowRequest>, DeleteThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingShadowRequest> marshall(DeleteThingShadowRequest deleteThingShadowRequest) {
        if (deleteThingShadowRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingShadowRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteThingShadowRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/things/{thingName}/shadow".replace("{thingName}", deleteThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(deleteThingShadowRequest.getThingName()));
        if (deleteThingShadowRequest.getShadowName() != null) {
            defaultRequest.addParameter("name", StringUtils.fromString(deleteThingShadowRequest.getShadowName()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
