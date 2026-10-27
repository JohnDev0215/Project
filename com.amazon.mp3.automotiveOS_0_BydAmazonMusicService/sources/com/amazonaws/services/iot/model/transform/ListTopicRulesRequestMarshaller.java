package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTopicRulesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListTopicRulesRequestMarshaller implements Marshaller<Request<ListTopicRulesRequest>, ListTopicRulesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTopicRulesRequest> marshall(ListTopicRulesRequest listTopicRulesRequest) {
        if (listTopicRulesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListTopicRulesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listTopicRulesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listTopicRulesRequest.getTopic() != null) {
            defaultRequest.addParameter("topic", StringUtils.fromString(listTopicRulesRequest.getTopic()));
        }
        if (listTopicRulesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listTopicRulesRequest.getMaxResults()));
        }
        if (listTopicRulesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listTopicRulesRequest.getNextToken()));
        }
        if (listTopicRulesRequest.getRuleDisabled() != null) {
            defaultRequest.addParameter("ruleDisabled", StringUtils.fromBoolean(listTopicRulesRequest.getRuleDisabled()));
        }
        defaultRequest.setResourcePath("/rules");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
