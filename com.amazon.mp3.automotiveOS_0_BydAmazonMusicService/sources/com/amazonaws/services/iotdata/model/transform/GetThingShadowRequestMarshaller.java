package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetThingShadowRequestMarshaller implements Marshaller<Request<GetThingShadowRequest>, GetThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetThingShadowRequest> marshall(GetThingShadowRequest getThingShadowRequest) {
        if (getThingShadowRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetThingShadowRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getThingShadowRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/things/{thingName}/shadow".replace("{thingName}", getThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(getThingShadowRequest.getThingName()));
        if (getThingShadowRequest.getShadowName() != null) {
            defaultRequest.addParameter("name", StringUtils.fromString(getThingShadowRequest.getShadowName()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
