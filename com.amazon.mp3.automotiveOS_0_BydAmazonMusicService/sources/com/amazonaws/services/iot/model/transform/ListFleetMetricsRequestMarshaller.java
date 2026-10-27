package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListFleetMetricsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListFleetMetricsRequestMarshaller implements Marshaller<Request<ListFleetMetricsRequest>, ListFleetMetricsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListFleetMetricsRequest> marshall(ListFleetMetricsRequest listFleetMetricsRequest) {
        if (listFleetMetricsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListFleetMetricsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listFleetMetricsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listFleetMetricsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listFleetMetricsRequest.getNextToken()));
        }
        if (listFleetMetricsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listFleetMetricsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/fleet-metrics");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
