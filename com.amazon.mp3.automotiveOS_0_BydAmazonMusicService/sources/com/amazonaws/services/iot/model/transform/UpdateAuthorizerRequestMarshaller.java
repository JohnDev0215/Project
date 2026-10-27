package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAuthorizerRequestMarshaller implements Marshaller<Request<UpdateAuthorizerRequest>, UpdateAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateAuthorizerRequest> marshall(UpdateAuthorizerRequest updateAuthorizerRequest) {
        if (updateAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", updateAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(updateAuthorizerRequest.getAuthorizerName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateAuthorizerRequest.getAuthorizerFunctionArn() != null) {
                String authorizerFunctionArn = updateAuthorizerRequest.getAuthorizerFunctionArn();
                jsonWriter.name("authorizerFunctionArn");
                jsonWriter.value(authorizerFunctionArn);
            }
            if (updateAuthorizerRequest.getTokenKeyName() != null) {
                String tokenKeyName = updateAuthorizerRequest.getTokenKeyName();
                jsonWriter.name("tokenKeyName");
                jsonWriter.value(tokenKeyName);
            }
            if (updateAuthorizerRequest.getTokenSigningPublicKeys() != null) {
                Map<String, String> tokenSigningPublicKeys = updateAuthorizerRequest.getTokenSigningPublicKeys();
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
            if (updateAuthorizerRequest.getStatus() != null) {
                String status = updateAuthorizerRequest.getStatus();
                jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
                jsonWriter.value(status);
            }
            if (updateAuthorizerRequest.getEnableCachingForHttp() != null) {
                Boolean enableCachingForHttp = updateAuthorizerRequest.getEnableCachingForHttp();
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
