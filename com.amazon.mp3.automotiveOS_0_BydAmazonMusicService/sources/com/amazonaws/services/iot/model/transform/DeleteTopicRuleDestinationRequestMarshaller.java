package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteTopicRuleDestinationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteTopicRuleDestinationRequestMarshaller implements Marshaller<Request<DeleteTopicRuleDestinationRequest>, DeleteTopicRuleDestinationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteTopicRuleDestinationRequest> marshall(DeleteTopicRuleDestinationRequest deleteTopicRuleDestinationRequest) {
        if (deleteTopicRuleDestinationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteTopicRuleDestinationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteTopicRuleDestinationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/destinations/{arn+}".replace("{arn}", deleteTopicRuleDestinationRequest.getArn() == null ? "" : StringUtils.fromString(deleteTopicRuleDestinationRequest.getArn())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
