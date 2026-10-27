package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListSecurityProfilesForTargetRequestMarshaller implements Marshaller<Request<ListSecurityProfilesForTargetRequest>, ListSecurityProfilesForTargetRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListSecurityProfilesForTargetRequest> marshall(ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest) {
        if (listSecurityProfilesForTargetRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListSecurityProfilesForTargetRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listSecurityProfilesForTargetRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listSecurityProfilesForTargetRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listSecurityProfilesForTargetRequest.getNextToken()));
        }
        if (listSecurityProfilesForTargetRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listSecurityProfilesForTargetRequest.getMaxResults()));
        }
        if (listSecurityProfilesForTargetRequest.getRecursive() != null) {
            defaultRequest.addParameter("recursive", StringUtils.fromBoolean(listSecurityProfilesForTargetRequest.getRecursive()));
        }
        if (listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn() != null) {
            defaultRequest.addParameter("securityProfileTargetArn", StringUtils.fromString(listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn()));
        }
        defaultRequest.setResourcePath("/security-profiles-for-target");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
