package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteFleetMetricRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteFleetMetricRequestMarshaller implements Marshaller<Request<DeleteFleetMetricRequest>, DeleteFleetMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteFleetMetricRequest> marshall(DeleteFleetMetricRequest deleteFleetMetricRequest) {
        if (deleteFleetMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteFleetMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteFleetMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/fleet-metric/{metricName}".replace("{metricName}", deleteFleetMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(deleteFleetMetricRequest.getMetricName()));
        if (deleteFleetMetricRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteFleetMetricRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
