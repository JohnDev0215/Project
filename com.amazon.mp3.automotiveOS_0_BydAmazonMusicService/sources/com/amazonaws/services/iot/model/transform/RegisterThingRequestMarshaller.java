package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegisterThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterThingRequestMarshaller implements Marshaller<Request<RegisterThingRequest>, RegisterThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RegisterThingRequest> marshall(RegisterThingRequest registerThingRequest) {
        if (registerThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(RegisterThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(registerThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/things");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (registerThingRequest.getTemplateBody() != null) {
                String templateBody = registerThingRequest.getTemplateBody();
                jsonWriter.name("templateBody");
                jsonWriter.value(templateBody);
            }
            if (registerThingRequest.getParameters() != null) {
                Map<String, String> parameters = registerThingRequest.getParameters();
                jsonWriter.name("parameters");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
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
