package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteTopicRuleRequestMarshaller implements Marshaller<Request<DeleteTopicRuleRequest>, DeleteTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteTopicRuleRequest> marshall(DeleteTopicRuleRequest deleteTopicRuleRequest) {
        if (deleteTopicRuleRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteTopicRuleRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteTopicRuleRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/rules/{ruleName}".replace("{ruleName}", deleteTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(deleteTopicRuleRequest.getRuleName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
