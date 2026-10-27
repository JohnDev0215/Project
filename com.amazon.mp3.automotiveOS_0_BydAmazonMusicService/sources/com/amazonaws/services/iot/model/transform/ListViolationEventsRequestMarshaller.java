package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListViolationEventsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListViolationEventsRequestMarshaller implements Marshaller<Request<ListViolationEventsRequest>, ListViolationEventsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListViolationEventsRequest> marshall(ListViolationEventsRequest listViolationEventsRequest) {
        if (listViolationEventsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListViolationEventsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listViolationEventsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listViolationEventsRequest.getStartTime() != null) {
            defaultRequest.addParameter("startTime", StringUtils.fromDate(listViolationEventsRequest.getStartTime()));
        }
        if (listViolationEventsRequest.getEndTime() != null) {
            defaultRequest.addParameter("endTime", StringUtils.fromDate(listViolationEventsRequest.getEndTime()));
        }
        if (listViolationEventsRequest.getThingName() != null) {
            defaultRequest.addParameter("thingName", StringUtils.fromString(listViolationEventsRequest.getThingName()));
        }
        if (listViolationEventsRequest.getSecurityProfileName() != null) {
            defaultRequest.addParameter("securityProfileName", StringUtils.fromString(listViolationEventsRequest.getSecurityProfileName()));
        }
        if (listViolationEventsRequest.getBehaviorCriteriaType() != null) {
            defaultRequest.addParameter("behaviorCriteriaType", StringUtils.fromString(listViolationEventsRequest.getBehaviorCriteriaType()));
        }
        if (listViolationEventsRequest.getListSuppressedAlerts() != null) {
            defaultRequest.addParameter("listSuppressedAlerts", StringUtils.fromBoolean(listViolationEventsRequest.getListSuppressedAlerts()));
        }
        if (listViolationEventsRequest.getVerificationState() != null) {
            defaultRequest.addParameter("verificationState", StringUtils.fromString(listViolationEventsRequest.getVerificationState()));
        }
        if (listViolationEventsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listViolationEventsRequest.getNextToken()));
        }
        if (listViolationEventsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listViolationEventsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/violation-events");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
