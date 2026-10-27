package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCustomMetricsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListCustomMetricsRequestMarshaller implements Marshaller<Request<ListCustomMetricsRequest>, ListCustomMetricsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCustomMetricsRequest> marshall(ListCustomMetricsRequest listCustomMetricsRequest) {
        if (listCustomMetricsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListCustomMetricsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listCustomMetricsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listCustomMetricsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listCustomMetricsRequest.getNextToken()));
        }
        if (listCustomMetricsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listCustomMetricsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/custom-metrics");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
