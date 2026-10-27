package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeStreamRequestMarshaller implements Marshaller<Request<DescribeStreamRequest>, DescribeStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeStreamRequest> marshall(DescribeStreamRequest describeStreamRequest) {
        if (describeStreamRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeStreamRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeStreamRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/streams/{streamId}".replace("{streamId}", describeStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(describeStreamRequest.getStreamId())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
