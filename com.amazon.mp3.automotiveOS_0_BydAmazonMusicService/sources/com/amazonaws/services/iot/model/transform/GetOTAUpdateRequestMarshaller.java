package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetOTAUpdateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetOTAUpdateRequestMarshaller implements Marshaller<Request<GetOTAUpdateRequest>, GetOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetOTAUpdateRequest> marshall(GetOTAUpdateRequest getOTAUpdateRequest) {
        if (getOTAUpdateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetOTAUpdateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getOTAUpdateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/otaUpdates/{otaUpdateId}".replace("{otaUpdateId}", getOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(getOTAUpdateRequest.getOtaUpdateId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
