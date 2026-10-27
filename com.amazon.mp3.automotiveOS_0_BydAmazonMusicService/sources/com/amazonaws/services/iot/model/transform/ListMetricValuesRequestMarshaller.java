package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListMetricValuesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListMetricValuesRequestMarshaller implements Marshaller<Request<ListMetricValuesRequest>, ListMetricValuesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListMetricValuesRequest> marshall(ListMetricValuesRequest listMetricValuesRequest) {
        if (listMetricValuesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListMetricValuesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listMetricValuesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listMetricValuesRequest.getThingName() != null) {
            defaultRequest.addParameter("thingName", StringUtils.fromString(listMetricValuesRequest.getThingName()));
        }
        if (listMetricValuesRequest.getMetricName() != null) {
            defaultRequest.addParameter("metricName", StringUtils.fromString(listMetricValuesRequest.getMetricName()));
        }
        if (listMetricValuesRequest.getDimensionName() != null) {
            defaultRequest.addParameter("dimensionName", StringUtils.fromString(listMetricValuesRequest.getDimensionName()));
        }
        if (listMetricValuesRequest.getDimensionValueOperator() != null) {
            defaultRequest.addParameter("dimensionValueOperator", StringUtils.fromString(listMetricValuesRequest.getDimensionValueOperator()));
        }
        if (listMetricValuesRequest.getStartTime() != null) {
            defaultRequest.addParameter("startTime", StringUtils.fromDate(listMetricValuesRequest.getStartTime()));
        }
        if (listMetricValuesRequest.getEndTime() != null) {
            defaultRequest.addParameter("endTime", StringUtils.fromDate(listMetricValuesRequest.getEndTime()));
        }
        if (listMetricValuesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listMetricValuesRequest.getMaxResults()));
        }
        if (listMetricValuesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listMetricValuesRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/metric-values");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
