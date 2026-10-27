package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleRequestMarshaller implements Marshaller<Request<CreateTopicRuleRequest>, CreateTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateTopicRuleRequest> marshall(CreateTopicRuleRequest createTopicRuleRequest) {
        if (createTopicRuleRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateTopicRuleRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createTopicRuleRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        if (createTopicRuleRequest.getTags() != null) {
            defaultRequest.addHeader("x-amz-tagging", StringUtils.fromString(createTopicRuleRequest.getTags()));
        }
        defaultRequest.setResourcePath("/rules/{ruleName}".replace("{ruleName}", createTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(createTopicRuleRequest.getRuleName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            if (createTopicRuleRequest.getTopicRulePayload() != null) {
                TopicRulePayloadJsonMarshaller.getInstance().marshall(createTopicRuleRequest.getTopicRulePayload(), jsonWriter);
            }
            jsonWriter.close();
            String string = stringWriter.toString();
            byte[] bytes = string.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(string));
            defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, Integer.toString(bytes.length));
            if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
                defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
            }
            return defaultRequest;
        } catch (Throwable th) {
            throw new AmazonClientException("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
