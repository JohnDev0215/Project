package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListSecurityProfilesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListSecurityProfilesRequestMarshaller implements Marshaller<Request<ListSecurityProfilesRequest>, ListSecurityProfilesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListSecurityProfilesRequest> marshall(ListSecurityProfilesRequest listSecurityProfilesRequest) {
        if (listSecurityProfilesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListSecurityProfilesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listSecurityProfilesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listSecurityProfilesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listSecurityProfilesRequest.getNextToken()));
        }
        if (listSecurityProfilesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listSecurityProfilesRequest.getMaxResults()));
        }
        if (listSecurityProfilesRequest.getDimensionName() != null) {
            defaultRequest.addParameter("dimensionName", StringUtils.fromString(listSecurityProfilesRequest.getDimensionName()));
        }
        if (listSecurityProfilesRequest.getMetricName() != null) {
            defaultRequest.addParameter("metricName", StringUtils.fromString(listSecurityProfilesRequest.getMetricName()));
        }
        defaultRequest.setResourcePath("/security-profiles");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
