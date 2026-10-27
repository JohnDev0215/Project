package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteMitigationActionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteMitigationActionRequestMarshaller implements Marshaller<Request<DeleteMitigationActionRequest>, DeleteMitigationActionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteMitigationActionRequest> marshall(DeleteMitigationActionRequest deleteMitigationActionRequest) {
        if (deleteMitigationActionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteMitigationActionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteMitigationActionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/mitigationactions/actions/{actionName}".replace("{actionName}", deleteMitigationActionRequest.getActionName() == null ? "" : StringUtils.fromString(deleteMitigationActionRequest.getActionName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
