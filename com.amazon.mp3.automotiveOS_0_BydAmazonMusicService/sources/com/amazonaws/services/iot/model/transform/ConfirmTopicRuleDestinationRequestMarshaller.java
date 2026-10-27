package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ConfirmTopicRuleDestinationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmTopicRuleDestinationRequestMarshaller implements Marshaller<Request<ConfirmTopicRuleDestinationRequest>, ConfirmTopicRuleDestinationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ConfirmTopicRuleDestinationRequest> marshall(ConfirmTopicRuleDestinationRequest confirmTopicRuleDestinationRequest) {
        if (confirmTopicRuleDestinationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ConfirmTopicRuleDestinationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(confirmTopicRuleDestinationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/confirmdestination/{confirmationToken+}".replace("{confirmationToken}", confirmTopicRuleDestinationRequest.getConfirmationToken() == null ? "" : StringUtils.fromString(confirmTopicRuleDestinationRequest.getConfirmationToken())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
