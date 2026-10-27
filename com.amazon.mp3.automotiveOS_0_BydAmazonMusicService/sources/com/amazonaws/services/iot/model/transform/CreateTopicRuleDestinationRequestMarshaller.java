package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.TopicRuleDestinationConfiguration;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class CreateTopicRuleDestinationRequestMarshaller implements Marshaller<Request<CreateTopicRuleDestinationRequest>, CreateTopicRuleDestinationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateTopicRuleDestinationRequest> marshall(CreateTopicRuleDestinationRequest createTopicRuleDestinationRequest) {
        if (createTopicRuleDestinationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateTopicRuleDestinationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createTopicRuleDestinationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/destinations");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createTopicRuleDestinationRequest.getDestinationConfiguration() != null) {
                TopicRuleDestinationConfiguration destinationConfiguration = createTopicRuleDestinationRequest.getDestinationConfiguration();
                jsonWriter.name("destinationConfiguration");
                TopicRuleDestinationConfigurationJsonMarshaller.getInstance().marshall(destinationConfiguration, jsonWriter);
            }
            jsonWriter.endObject();
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
