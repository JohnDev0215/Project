package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeCustomMetricRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCustomMetricRequestMarshaller implements Marshaller<Request<DescribeCustomMetricRequest>, DescribeCustomMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeCustomMetricRequest> marshall(DescribeCustomMetricRequest describeCustomMetricRequest) {
        if (describeCustomMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeCustomMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeCustomMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/custom-metric/{metricName}".replace("{metricName}", describeCustomMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(describeCustomMetricRequest.getMetricName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
