package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteDimensionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDimensionRequestMarshaller implements Marshaller<Request<DeleteDimensionRequest>, DeleteDimensionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteDimensionRequest> marshall(DeleteDimensionRequest deleteDimensionRequest) {
        if (deleteDimensionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteDimensionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteDimensionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/dimensions/{name}".replace("{name}", deleteDimensionRequest.getName() == null ? "" : StringUtils.fromString(deleteDimensionRequest.getName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
