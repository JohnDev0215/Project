package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForSecurityProfileRequestMarshaller implements Marshaller<Request<ListTargetsForSecurityProfileRequest>, ListTargetsForSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTargetsForSecurityProfileRequest> marshall(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) {
        if (listTargetsForSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListTargetsForSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listTargetsForSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", listTargetsForSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(listTargetsForSecurityProfileRequest.getSecurityProfileName()));
        if (listTargetsForSecurityProfileRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listTargetsForSecurityProfileRequest.getNextToken()));
        }
        if (listTargetsForSecurityProfileRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listTargetsForSecurityProfileRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
