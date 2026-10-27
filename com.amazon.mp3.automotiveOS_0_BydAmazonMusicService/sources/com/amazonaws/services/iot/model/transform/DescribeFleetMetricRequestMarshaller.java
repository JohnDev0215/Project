package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeFleetMetricRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeFleetMetricRequestMarshaller implements Marshaller<Request<DescribeFleetMetricRequest>, DescribeFleetMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeFleetMetricRequest> marshall(DescribeFleetMetricRequest describeFleetMetricRequest) {
        if (describeFleetMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeFleetMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeFleetMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/fleet-metric/{metricName}".replace("{metricName}", describeFleetMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(describeFleetMetricRequest.getMetricName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
