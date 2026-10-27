package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateAuthorizerRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuthorizerRequestMarshaller implements Marshaller<Request<CreateAuthorizerRequest>, CreateAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateAuthorizerRequest> marshall(CreateAuthorizerRequest createAuthorizerRequest) {
        if (createAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", createAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(createAuthorizerRequest.getAuthorizerName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createAuthorizerRequest.getAuthorizerFunctionArn() != null) {
                String authorizerFunctionArn = createAuthorizerRequest.getAuthorizerFunctionArn();
                jsonWriter.name("authorizerFunctionArn");
                jsonWriter.value(authorizerFunctionArn);
            }
            if (createAuthorizerRequest.getTokenKeyName() != null) {
                String tokenKeyName = createAuthorizerRequest.getTokenKeyName();
                jsonWriter.name("tokenKeyName");
                jsonWriter.value(tokenKeyName);
            }
            if (createAuthorizerRequest.getTokenSigningPublicKeys() != null) {
                Map<String, String> tokenSigningPublicKeys = createAuthorizerRequest.getTokenSigningPublicKeys();
                jsonWriter.name("tokenSigningPublicKeys");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : tokenSigningPublicKeys.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
            }
            if (createAuthorizerRequest.getStatus() != null) {
                String status = createAuthorizerRequest.getStatus();
                jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
                jsonWriter.value(status);
            }
            if (createAuthorizerRequest.getTags() != null) {
                List<Tag> tags = createAuthorizerRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createAuthorizerRequest.getSigningDisabled() != null) {
                Boolean signingDisabled = createAuthorizerRequest.getSigningDisabled();
                jsonWriter.name("signingDisabled");
                jsonWriter.value(signingDisabled.booleanValue());
            }
            if (createAuthorizerRequest.getEnableCachingForHttp() != null) {
                Boolean enableCachingForHttp = createAuthorizerRequest.getEnableCachingForHttp();
                jsonWriter.name("enableCachingForHttp");
                jsonWriter.value(enableCachingForHttp.booleanValue());
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
