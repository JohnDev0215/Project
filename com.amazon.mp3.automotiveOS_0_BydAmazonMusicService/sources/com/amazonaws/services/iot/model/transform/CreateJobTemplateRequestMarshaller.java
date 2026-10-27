package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.CreateJobTemplateRequest;
import com.amazonaws.services.iot.model.JobExecutionsRetryConfig;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateJobTemplateRequestMarshaller implements Marshaller<Request<CreateJobTemplateRequest>, CreateJobTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateJobTemplateRequest> marshall(CreateJobTemplateRequest createJobTemplateRequest) {
        if (createJobTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateJobTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createJobTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/job-templates/{jobTemplateId}".replace("{jobTemplateId}", createJobTemplateRequest.getJobTemplateId() == null ? "" : StringUtils.fromString(createJobTemplateRequest.getJobTemplateId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createJobTemplateRequest.getJobArn() != null) {
                String jobArn = createJobTemplateRequest.getJobArn();
                jsonWriter.name("jobArn");
                jsonWriter.value(jobArn);
            }
            if (createJobTemplateRequest.getDocumentSource() != null) {
                String documentSource = createJobTemplateRequest.getDocumentSource();
                jsonWriter.name("documentSource");
                jsonWriter.value(documentSource);
            }
            if (createJobTemplateRequest.getDocument() != null) {
                String document = createJobTemplateRequest.getDocument();
                jsonWriter.name("document");
                jsonWriter.value(document);
            }
            if (createJobTemplateRequest.getDescription() != null) {
                String description = createJobTemplateRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createJobTemplateRequest.getPresignedUrlConfig() != null) {
                PresignedUrlConfig presignedUrlConfig = createJobTemplateRequest.getPresignedUrlConfig();
                jsonWriter.name("presignedUrlConfig");
                PresignedUrlConfigJsonMarshaller.getInstance().marshall(presignedUrlConfig, jsonWriter);
            }
            if (createJobTemplateRequest.getJobExecutionsRolloutConfig() != null) {
                JobExecutionsRolloutConfig jobExecutionsRolloutConfig = createJobTemplateRequest.getJobExecutionsRolloutConfig();
                jsonWriter.name("jobExecutionsRolloutConfig");
                JobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(jobExecutionsRolloutConfig, jsonWriter);
            }
            if (createJobTemplateRequest.getAbortConfig() != null) {
                AbortConfig abortConfig = createJobTemplateRequest.getAbortConfig();
                jsonWriter.name("abortConfig");
                AbortConfigJsonMarshaller.getInstance().marshall(abortConfig, jsonWriter);
            }
            if (createJobTemplateRequest.getTimeoutConfig() != null) {
                TimeoutConfig timeoutConfig = createJobTemplateRequest.getTimeoutConfig();
                jsonWriter.name("timeoutConfig");
                TimeoutConfigJsonMarshaller.getInstance().marshall(timeoutConfig, jsonWriter);
            }
            if (createJobTemplateRequest.getTags() != null) {
                List<Tag> tags = createJobTemplateRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createJobTemplateRequest.getJobExecutionsRetryConfig() != null) {
                JobExecutionsRetryConfig jobExecutionsRetryConfig = createJobTemplateRequest.getJobExecutionsRetryConfig();
                jsonWriter.name("jobExecutionsRetryConfig");
                JobExecutionsRetryConfigJsonMarshaller.getInstance().marshall(jobExecutionsRetryConfig, jsonWriter);
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
