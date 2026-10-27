package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.HttpContext;
import com.amazonaws.services.iot.model.MqttContext;
import com.amazonaws.services.iot.model.TestInvokeAuthorizerRequest;
import com.amazonaws.services.iot.model.TlsContext;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class TestInvokeAuthorizerRequestMarshaller implements Marshaller<Request<TestInvokeAuthorizerRequest>, TestInvokeAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<TestInvokeAuthorizerRequest> marshall(TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) {
        if (testInvokeAuthorizerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(TestInvokeAuthorizerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(testInvokeAuthorizerRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/authorizer/{authorizerName}/test".replace("{authorizerName}", testInvokeAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(testInvokeAuthorizerRequest.getAuthorizerName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (testInvokeAuthorizerRequest.getToken() != null) {
                String token = testInvokeAuthorizerRequest.getToken();
                jsonWriter.name("token");
                jsonWriter.value(token);
            }
            if (testInvokeAuthorizerRequest.getTokenSignature() != null) {
                String tokenSignature = testInvokeAuthorizerRequest.getTokenSignature();
                jsonWriter.name("tokenSignature");
                jsonWriter.value(tokenSignature);
            }
            if (testInvokeAuthorizerRequest.getHttpContext() != null) {
                HttpContext httpContext = testInvokeAuthorizerRequest.getHttpContext();
                jsonWriter.name("httpContext");
                HttpContextJsonMarshaller.getInstance().marshall(httpContext, jsonWriter);
            }
            if (testInvokeAuthorizerRequest.getMqttContext() != null) {
                MqttContext mqttContext = testInvokeAuthorizerRequest.getMqttContext();
                jsonWriter.name("mqttContext");
                MqttContextJsonMarshaller.getInstance().marshall(mqttContext, jsonWriter);
            }
            if (testInvokeAuthorizerRequest.getTlsContext() != null) {
                TlsContext tlsContext = testInvokeAuthorizerRequest.getTlsContext();
                jsonWriter.name("tlsContext");
                TlsContextJsonMarshaller.getInstance().marshall(tlsContext, jsonWriter);
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
