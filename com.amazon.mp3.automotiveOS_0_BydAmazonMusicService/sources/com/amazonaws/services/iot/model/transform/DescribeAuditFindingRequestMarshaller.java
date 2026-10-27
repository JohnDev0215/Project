package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuditFindingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditFindingRequestMarshaller implements Marshaller<Request<DescribeAuditFindingRequest>, DescribeAuditFindingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuditFindingRequest> marshall(DescribeAuditFindingRequest describeAuditFindingRequest) {
        if (describeAuditFindingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuditFindingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAuditFindingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/audit/findings/{findingId}".replace("{findingId}", describeAuditFindingRequest.getFindingId() == null ? "" : StringUtils.fromString(describeAuditFindingRequest.getFindingId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
