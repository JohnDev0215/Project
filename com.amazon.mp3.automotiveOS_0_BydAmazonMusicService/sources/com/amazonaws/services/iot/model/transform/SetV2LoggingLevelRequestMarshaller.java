package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.services.iot.model.SetV2LoggingLevelRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class SetV2LoggingLevelRequestMarshaller implements Marshaller<Request<SetV2LoggingLevelRequest>, SetV2LoggingLevelRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetV2LoggingLevelRequest> marshall(SetV2LoggingLevelRequest setV2LoggingLevelRequest) {
        if (setV2LoggingLevelRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SetV2LoggingLevelRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(setV2LoggingLevelRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/v2LoggingLevel");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (setV2LoggingLevelRequest.getLogTarget() != null) {
                LogTarget logTarget = setV2LoggingLevelRequest.getLogTarget();
                jsonWriter.name("logTarget");
                LogTargetJsonMarshaller.getInstance().marshall(logTarget, jsonWriter);
            }
            if (setV2LoggingLevelRequest.getLogLevel() != null) {
                String logLevel = setV2LoggingLevelRequest.getLogLevel();
                jsonWriter.name("logLevel");
                jsonWriter.value(logLevel);
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
