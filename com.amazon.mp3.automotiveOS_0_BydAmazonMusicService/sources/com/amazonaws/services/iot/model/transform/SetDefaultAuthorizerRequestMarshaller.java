package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SetDefaultAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class SetDefaultAuthorizerRequestMarshaller implements Marshaller<Request<SetDefaultAuthorizerRequest>, SetDefaultAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetDefaultAuthorizerRequest> marshall(SetDefaultAuthorizerRequest setDefaultAuthorizerRequest) {
        if (setDefaultAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SetDefaultAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(setDefaultAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/default-authorizer");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (setDefaultAuthorizerRequest.getAuthorizerName() != null) {
                String authorizerName = setDefaultAuthorizerRequest.getAuthorizerName();
                jsonWriter.name("authorizerName");
                jsonWriter.value(authorizerName);
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
