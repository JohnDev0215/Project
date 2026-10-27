package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeScheduledAuditRequestMarshaller implements Marshaller<Request<DescribeScheduledAuditRequest>, DescribeScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeScheduledAuditRequest> marshall(DescribeScheduledAuditRequest describeScheduledAuditRequest) {
        if (describeScheduledAuditRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeScheduledAuditRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeScheduledAuditRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/audit/scheduledaudits/{scheduledAuditName}".replace("{scheduledAuditName}", describeScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(describeScheduledAuditRequest.getScheduledAuditName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
