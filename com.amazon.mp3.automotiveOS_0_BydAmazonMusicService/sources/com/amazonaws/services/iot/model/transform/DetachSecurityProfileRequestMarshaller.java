package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DetachSecurityProfileRequestMarshaller implements Marshaller<Request<DetachSecurityProfileRequest>, DetachSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachSecurityProfileRequest> marshall(DetachSecurityProfileRequest detachSecurityProfileRequest) {
        if (detachSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DetachSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(detachSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", detachSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(detachSecurityProfileRequest.getSecurityProfileName()));
        if (detachSecurityProfileRequest.getSecurityProfileTargetArn() != null) {
            defaultRequest.addParameter("securityProfileTargetArn", StringUtils.fromString(detachSecurityProfileRequest.getSecurityProfileTargetArn()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
