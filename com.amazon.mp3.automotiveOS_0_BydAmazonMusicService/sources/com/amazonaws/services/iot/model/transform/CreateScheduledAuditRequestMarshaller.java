package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateScheduledAuditRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateScheduledAuditRequestMarshaller implements Marshaller<Request<CreateScheduledAuditRequest>, CreateScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateScheduledAuditRequest> marshall(CreateScheduledAuditRequest createScheduledAuditRequest) {
        if (createScheduledAuditRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateScheduledAuditRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createScheduledAuditRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/audit/scheduledaudits/{scheduledAuditName}".replace("{scheduledAuditName}", createScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(createScheduledAuditRequest.getScheduledAuditName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createScheduledAuditRequest.getFrequency() != null) {
                String frequency = createScheduledAuditRequest.getFrequency();
                jsonWriter.name("frequency");
                jsonWriter.value(frequency);
            }
            if (createScheduledAuditRequest.getDayOfMonth() != null) {
                String dayOfMonth = createScheduledAuditRequest.getDayOfMonth();
                jsonWriter.name("dayOfMonth");
                jsonWriter.value(dayOfMonth);
            }
            if (createScheduledAuditRequest.getDayOfWeek() != null) {
                String dayOfWeek = createScheduledAuditRequest.getDayOfWeek();
                jsonWriter.name("dayOfWeek");
                jsonWriter.value(dayOfWeek);
            }
            if (createScheduledAuditRequest.getTargetCheckNames() != null) {
                List<String> targetCheckNames = createScheduledAuditRequest.getTargetCheckNames();
                jsonWriter.name("targetCheckNames");
                jsonWriter.beginArray();
                for (String str : targetCheckNames) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createScheduledAuditRequest.getTags() != null) {
                List<Tag> tags = createScheduledAuditRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
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
