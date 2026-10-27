package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateCustomMetricRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCustomMetricRequestMarshaller implements Marshaller<Request<CreateCustomMetricRequest>, CreateCustomMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateCustomMetricRequest> marshall(CreateCustomMetricRequest createCustomMetricRequest) {
        if (createCustomMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateCustomMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createCustomMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/custom-metric/{metricName}".replace("{metricName}", createCustomMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(createCustomMetricRequest.getMetricName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createCustomMetricRequest.getDisplayName() != null) {
                String displayName = createCustomMetricRequest.getDisplayName();
                jsonWriter.name("displayName");
                jsonWriter.value(displayName);
            }
            if (createCustomMetricRequest.getMetricType() != null) {
                String metricType = createCustomMetricRequest.getMetricType();
                jsonWriter.name("metricType");
                jsonWriter.value(metricType);
            }
            if (createCustomMetricRequest.getTags() != null) {
                List<Tag> tags = createCustomMetricRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createCustomMetricRequest.getClientRequestToken() != null) {
                String clientRequestToken = createCustomMetricRequest.getClientRequestToken();
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
