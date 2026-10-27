package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.EnableTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class EnableTopicRuleRequestMarshaller implements Marshaller<Request<EnableTopicRuleRequest>, EnableTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<EnableTopicRuleRequest> marshall(EnableTopicRuleRequest enableTopicRuleRequest) {
        if (enableTopicRuleRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(EnableTopicRuleRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(enableTopicRuleRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/rules/{ruleName}/enable".replace("{ruleName}", enableTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(enableTopicRuleRequest.getRuleName())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
