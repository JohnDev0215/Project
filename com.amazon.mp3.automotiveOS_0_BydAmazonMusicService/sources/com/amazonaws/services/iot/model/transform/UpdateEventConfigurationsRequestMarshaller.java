package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.Configuration;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateEventConfigurationsRequestMarshaller implements Marshaller<Request<UpdateEventConfigurationsRequest>, UpdateEventConfigurationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateEventConfigurationsRequest> marshall(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) {
        if (updateEventConfigurationsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateEventConfigurationsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateEventConfigurationsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/event-configurations");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateEventConfigurationsRequest.getEventConfigurations() != null) {
                Map<String, Configuration> eventConfigurations = updateEventConfigurationsRequest.getEventConfigurations();
                jsonWriter.name("eventConfigurations");
                jsonWriter.beginObject();
                for (Map.Entry<String, Configuration> entry : eventConfigurations.entrySet()) {
                    Configuration value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        ConfigurationJsonMarshaller.getInstance().marshall(value, jsonWriter);
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
