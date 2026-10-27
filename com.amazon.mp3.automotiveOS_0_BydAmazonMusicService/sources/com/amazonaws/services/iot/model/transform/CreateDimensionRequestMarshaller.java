package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateDimensionRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDimensionRequestMarshaller implements Marshaller<Request<CreateDimensionRequest>, CreateDimensionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateDimensionRequest> marshall(CreateDimensionRequest createDimensionRequest) {
        if (createDimensionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateDimensionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createDimensionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/dimensions/{name}".replace("{name}", createDimensionRequest.getName() == null ? "" : StringUtils.fromString(createDimensionRequest.getName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createDimensionRequest.getType() != null) {
                String type = createDimensionRequest.getType();
                jsonWriter.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                jsonWriter.value(type);
            }
            if (createDimensionRequest.getStringValues() != null) {
                List<String> stringValues = createDimensionRequest.getStringValues();
                jsonWriter.name("stringValues");
                jsonWriter.beginArray();
                for (String str : stringValues) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createDimensionRequest.getTags() != null) {
                List<Tag> tags = createDimensionRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createDimensionRequest.getClientRequestToken() != null) {
                String clientRequestToken = createDimensionRequest.getClientRequestToken();
                jsonWriter.name("clientRequestToken");
                jsonWriter.value(clientRequestToken);
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
