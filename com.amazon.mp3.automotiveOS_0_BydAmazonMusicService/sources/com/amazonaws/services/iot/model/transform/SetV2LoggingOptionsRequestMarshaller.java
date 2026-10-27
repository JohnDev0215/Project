package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class SetV2LoggingOptionsRequestMarshaller implements Marshaller<Request<SetV2LoggingOptionsRequest>, SetV2LoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetV2LoggingOptionsRequest> marshall(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) {
        if (setV2LoggingOptionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SetV2LoggingOptionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(setV2LoggingOptionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/v2LoggingOptions");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (setV2LoggingOptionsRequest.getRoleArn() != null) {
                String roleArn = setV2LoggingOptionsRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (setV2LoggingOptionsRequest.getDefaultLogLevel() != null) {
                String defaultLogLevel = setV2LoggingOptionsRequest.getDefaultLogLevel();
                jsonWriter.name("defaultLogLevel");
                jsonWriter.value(defaultLogLevel);
            }
            if (setV2LoggingOptionsRequest.getDisableAllLogs() != null) {
                Boolean disableAllLogs = setV2LoggingOptionsRequest.getDisableAllLogs();
                jsonWriter.name("disableAllLogs");
                jsonWriter.value(disableAllLogs.booleanValue());
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
