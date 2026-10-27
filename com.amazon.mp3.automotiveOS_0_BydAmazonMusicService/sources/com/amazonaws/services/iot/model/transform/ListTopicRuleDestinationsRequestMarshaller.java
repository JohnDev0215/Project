package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTopicRuleDestinationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRuleDestinationsRequestMarshaller implements Marshaller<Request<ListTopicRuleDestinationsRequest>, ListTopicRuleDestinationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTopicRuleDestinationsRequest> marshall(ListTopicRuleDestinationsRequest listTopicRuleDestinationsRequest) {
        if (listTopicRuleDestinationsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListTopicRuleDestinationsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listTopicRuleDestinationsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listTopicRuleDestinationsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listTopicRuleDestinationsRequest.getMaxResults()));
        }
        if (listTopicRuleDestinationsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listTopicRuleDestinationsRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/destinations");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
