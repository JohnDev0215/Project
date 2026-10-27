package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class AttachSecurityProfileRequestMarshaller implements Marshaller<Request<AttachSecurityProfileRequest>, AttachSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachSecurityProfileRequest> marshall(AttachSecurityProfileRequest attachSecurityProfileRequest) {
        if (attachSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AttachSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(attachSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        String strReplace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", attachSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(attachSecurityProfileRequest.getSecurityProfileName()));
        if (attachSecurityProfileRequest.getSecurityProfileTargetArn() != null) {
            defaultRequest.addParameter("securityProfileTargetArn", StringUtils.fromString(attachSecurityProfileRequest.getSecurityProfileTargetArn()));
        }
        defaultRequest.setResourcePath(strReplace);
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
