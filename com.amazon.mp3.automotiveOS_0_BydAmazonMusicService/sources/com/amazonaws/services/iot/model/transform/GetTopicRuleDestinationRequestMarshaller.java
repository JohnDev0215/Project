package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetTopicRuleDestinationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetTopicRuleDestinationRequestMarshaller implements Marshaller<Request<GetTopicRuleDestinationRequest>, GetTopicRuleDestinationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetTopicRuleDestinationRequest> marshall(GetTopicRuleDestinationRequest getTopicRuleDestinationRequest) {
        if (getTopicRuleDestinationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetTopicRuleDestinationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getTopicRuleDestinationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/destinations/{arn+}".replace("{arn}", getTopicRuleDestinationRequest.getArn() == null ? "" : StringUtils.fromString(getTopicRuleDestinationRequest.getArn())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
