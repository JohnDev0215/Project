package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeIndexRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeIndexRequestMarshaller implements Marshaller<Request<DescribeIndexRequest>, DescribeIndexRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeIndexRequest> marshall(DescribeIndexRequest describeIndexRequest) {
        if (describeIndexRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeIndexRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeIndexRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/indices/{indexName}".replace("{indexName}", describeIndexRequest.getIndexName() == null ? "" : StringUtils.fromString(describeIndexRequest.getIndexName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
