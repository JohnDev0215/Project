package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ReplaceTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class ReplaceTopicRuleRequestMarshaller implements Marshaller<Request<ReplaceTopicRuleRequest>, ReplaceTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ReplaceTopicRuleRequest> marshall(ReplaceTopicRuleRequest replaceTopicRuleRequest) {
        if (replaceTopicRuleRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ReplaceTopicRuleRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(replaceTopicRuleRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/rules/{ruleName}".replace("{ruleName}", replaceTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(replaceTopicRuleRequest.getRuleName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            if (replaceTopicRuleRequest.getTopicRulePayload() != null) {
                TopicRulePayloadJsonMarshaller.getInstance().marshall(replaceTopicRuleRequest.getTopicRulePayload(), jsonWriter);
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
