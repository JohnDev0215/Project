package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteCustomMetricRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteCustomMetricRequestMarshaller implements Marshaller<Request<DeleteCustomMetricRequest>, DeleteCustomMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteCustomMetricRequest> marshall(DeleteCustomMetricRequest deleteCustomMetricRequest) {
        if (deleteCustomMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteCustomMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteCustomMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/custom-metric/{metricName}".replace("{metricName}", deleteCustomMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(deleteCustomMetricRequest.getMetricName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
