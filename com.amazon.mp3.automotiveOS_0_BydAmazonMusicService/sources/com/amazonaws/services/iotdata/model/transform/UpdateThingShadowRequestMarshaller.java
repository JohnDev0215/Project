package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingShadowRequestMarshaller implements Marshaller<Request<UpdateThingShadowRequest>, UpdateThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingShadowRequest> marshall(UpdateThingShadowRequest updateThingShadowRequest) {
        if (updateThingShadowRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingShadowRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateThingShadowRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/things/{thingName}/shadow".replace("{thingName}", updateThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(updateThingShadowRequest.getThingName()));
        if (updateThingShadowRequest.getShadowName() != null) {
            defaultRequest.addParameter("name", StringUtils.fromString(updateThingShadowRequest.getShadowName()));
        }
        defaultRequest.setResourcePath(strReplace);
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, Integer.toString(updateThingShadowRequest.getPayload().remaining()));
        defaultRequest.setContent(BinaryUtils.toStream(updateThingShadowRequest.getPayload()));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
