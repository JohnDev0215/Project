package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateScheduledAuditRequestMarshaller implements Marshaller<Request<UpdateScheduledAuditRequest>, UpdateScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateScheduledAuditRequest> marshall(UpdateScheduledAuditRequest updateScheduledAuditRequest) {
        if (updateScheduledAuditRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateScheduledAuditRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateScheduledAuditRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/audit/scheduledaudits/{scheduledAuditName}".replace("{scheduledAuditName}", updateScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(updateScheduledAuditRequest.getScheduledAuditName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateScheduledAuditRequest.getFrequency() != null) {
                String frequency = updateScheduledAuditRequest.getFrequency();
                jsonWriter.name("frequency");
                jsonWriter.value(frequency);
            }
            if (updateScheduledAuditRequest.getDayOfMonth() != null) {
                String dayOfMonth = updateScheduledAuditRequest.getDayOfMonth();
                jsonWriter.name("dayOfMonth");
                jsonWriter.value(dayOfMonth);
            }
            if (updateScheduledAuditRequest.getDayOfWeek() != null) {
                String dayOfWeek = updateScheduledAuditRequest.getDayOfWeek();
                jsonWriter.name("dayOfWeek");
                jsonWriter.value(dayOfWeek);
            }
            if (updateScheduledAuditRequest.getTargetCheckNames() != null) {
                List<String> targetCheckNames = updateScheduledAuditRequest.getTargetCheckNames();
                jsonWriter.name("targetCheckNames");
                jsonWriter.beginArray();
                for (String str : targetCheckNames) {
                    if (str != null) {
                        jsonWriter.value(str);
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
