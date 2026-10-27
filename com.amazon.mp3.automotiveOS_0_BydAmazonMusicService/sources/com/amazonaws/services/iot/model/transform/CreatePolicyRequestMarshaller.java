package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreatePolicyRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreatePolicyRequestMarshaller implements Marshaller<Request<CreatePolicyRequest>, CreatePolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreatePolicyRequest> marshall(CreatePolicyRequest createPolicyRequest) {
        if (createPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreatePolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/policies/{policyName}".replace("{policyName}", createPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(createPolicyRequest.getPolicyName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createPolicyRequest.getPolicyDocument() != null) {
                String policyDocument = createPolicyRequest.getPolicyDocument();
                jsonWriter.name("policyDocument");
                jsonWriter.value(policyDocument);
            }
            if (createPolicyRequest.getTags() != null) {
                List<Tag> tags = createPolicyRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
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
