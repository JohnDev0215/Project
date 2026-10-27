package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListActiveViolationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListActiveViolationsRequestMarshaller implements Marshaller<Request<ListActiveViolationsRequest>, ListActiveViolationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListActiveViolationsRequest> marshall(ListActiveViolationsRequest listActiveViolationsRequest) {
        if (listActiveViolationsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListActiveViolationsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listActiveViolationsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listActiveViolationsRequest.getThingName() != null) {
            defaultRequest.addParameter("thingName", StringUtils.fromString(listActiveViolationsRequest.getThingName()));
        }
        if (listActiveViolationsRequest.getSecurityProfileName() != null) {
            defaultRequest.addParameter("securityProfileName", StringUtils.fromString(listActiveViolationsRequest.getSecurityProfileName()));
        }
        if (listActiveViolationsRequest.getBehaviorCriteriaType() != null) {
            defaultRequest.addParameter("behaviorCriteriaType", StringUtils.fromString(listActiveViolationsRequest.getBehaviorCriteriaType()));
        }
        if (listActiveViolationsRequest.getListSuppressedAlerts() != null) {
            defaultRequest.addParameter("listSuppressedAlerts", StringUtils.fromBoolean(listActiveViolationsRequest.getListSuppressedAlerts()));
        }
        if (listActiveViolationsRequest.getVerificationState() != null) {
            defaultRequest.addParameter("verificationState", StringUtils.fromString(listActiveViolationsRequest.getVerificationState()));
        }
        if (listActiveViolationsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listActiveViolationsRequest.getNextToken()));
        }
        if (listActiveViolationsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listActiveViolationsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/active-violations");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
