package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.JobExecutionsRetryConfig;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.services.iot.model.UpdateJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateJobRequestMarshaller implements Marshaller<Request<UpdateJobRequest>, UpdateJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateJobRequest> marshall(UpdateJobRequest updateJobRequest) {
        if (updateJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        String strReplace = "/jobs/{jobId}".replace("{jobId}", updateJobRequest.getJobId() == null ? "" : StringUtils.fromString(updateJobRequest.getJobId()));
        if (updateJobRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(updateJobRequest.getNamespaceId()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateJobRequest.getDescription() != null) {
                String description = updateJobRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (updateJobRequest.getPresignedUrlConfig() != null) {
                PresignedUrlConfig presignedUrlConfig = updateJobRequest.getPresignedUrlConfig();
                jsonWriter.name("presignedUrlConfig");
                PresignedUrlConfigJsonMarshaller.getInstance().marshall(presignedUrlConfig, jsonWriter);
            }
            if (updateJobRequest.getJobExecutionsRolloutConfig() != null) {
                JobExecutionsRolloutConfig jobExecutionsRolloutConfig = updateJobRequest.getJobExecutionsRolloutConfig();
                jsonWriter.name("jobExecutionsRolloutConfig");
                JobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(jobExecutionsRolloutConfig, jsonWriter);
            }
            if (updateJobRequest.getAbortConfig() != null) {
                AbortConfig abortConfig = updateJobRequest.getAbortConfig();
                jsonWriter.name("abortConfig");
                AbortConfigJsonMarshaller.getInstance().marshall(abortConfig, jsonWriter);
            }
            if (updateJobRequest.getTimeoutConfig() != null) {
                TimeoutConfig timeoutConfig = updateJobRequest.getTimeoutConfig();
                jsonWriter.name("timeoutConfig");
                TimeoutConfigJsonMarshaller.getInstance().marshall(timeoutConfig, jsonWriter);
            }
            if (updateJobRequest.getJobExecutionsRetryConfig() != null) {
                JobExecutionsRetryConfig jobExecutionsRetryConfig = updateJobRequest.getJobExecutionsRetryConfig();
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
