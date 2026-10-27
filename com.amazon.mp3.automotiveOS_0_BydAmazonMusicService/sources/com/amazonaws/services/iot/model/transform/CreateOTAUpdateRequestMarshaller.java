package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AwsJobAbortConfig;
import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.AwsJobPresignedUrlConfig;
import com.amazonaws.services.iot.model.AwsJobTimeoutConfig;
import com.amazonaws.services.iot.model.CreateOTAUpdateRequest;
import com.amazonaws.services.iot.model.OTAUpdateFile;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateOTAUpdateRequestMarshaller implements Marshaller<Request<CreateOTAUpdateRequest>, CreateOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateOTAUpdateRequest> marshall(CreateOTAUpdateRequest createOTAUpdateRequest) {
        if (createOTAUpdateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateOTAUpdateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createOTAUpdateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/otaUpdates/{otaUpdateId}".replace("{otaUpdateId}", createOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(createOTAUpdateRequest.getOtaUpdateId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createOTAUpdateRequest.getDescription() != null) {
                String description = createOTAUpdateRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createOTAUpdateRequest.getTargets() != null) {
                List<String> targets = createOTAUpdateRequest.getTargets();
                jsonWriter.name("targets");
                jsonWriter.beginArray();
                for (String str : targets) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createOTAUpdateRequest.getProtocols() != null) {
                List<String> protocols = createOTAUpdateRequest.getProtocols();
                jsonWriter.name("protocols");
                jsonWriter.beginArray();
                for (String str2 : protocols) {
                    if (str2 != null) {
                        jsonWriter.value(str2);
                    }
                }
                jsonWriter.endArray();
            }
            if (createOTAUpdateRequest.getTargetSelection() != null) {
                String targetSelection = createOTAUpdateRequest.getTargetSelection();
                jsonWriter.name("targetSelection");
                jsonWriter.value(targetSelection);
            }
            if (createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() != null) {
                AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig();
                jsonWriter.name("awsJobExecutionsRolloutConfig");
                AwsJobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(awsJobExecutionsRolloutConfig, jsonWriter);
            }
            if (createOTAUpdateRequest.getAwsJobPresignedUrlConfig() != null) {
                AwsJobPresignedUrlConfig awsJobPresignedUrlConfig = createOTAUpdateRequest.getAwsJobPresignedUrlConfig();
                jsonWriter.name("awsJobPresignedUrlConfig");
                AwsJobPresignedUrlConfigJsonMarshaller.getInstance().marshall(awsJobPresignedUrlConfig, jsonWriter);
            }
            if (createOTAUpdateRequest.getAwsJobAbortConfig() != null) {
                AwsJobAbortConfig awsJobAbortConfig = createOTAUpdateRequest.getAwsJobAbortConfig();
                jsonWriter.name("awsJobAbortConfig");
                AwsJobAbortConfigJsonMarshaller.getInstance().marshall(awsJobAbortConfig, jsonWriter);
            }
            if (createOTAUpdateRequest.getAwsJobTimeoutConfig() != null) {
                AwsJobTimeoutConfig awsJobTimeoutConfig = createOTAUpdateRequest.getAwsJobTimeoutConfig();
                jsonWriter.name("awsJobTimeoutConfig");
                AwsJobTimeoutConfigJsonMarshaller.getInstance().marshall(awsJobTimeoutConfig, jsonWriter);
            }
            if (createOTAUpdateRequest.getFiles() != null) {
                List<OTAUpdateFile> files = createOTAUpdateRequest.getFiles();
                jsonWriter.name("files");
                jsonWriter.beginArray();
                for (OTAUpdateFile oTAUpdateFile : files) {
                    if (oTAUpdateFile != null) {
                        OTAUpdateFileJsonMarshaller.getInstance().marshall(oTAUpdateFile, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createOTAUpdateRequest.getRoleArn() != null) {
                String roleArn = createOTAUpdateRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (createOTAUpdateRequest.getAdditionalParameters() != null) {
                Map<String, String> additionalParameters = createOTAUpdateRequest.getAdditionalParameters();
                jsonWriter.name("additionalParameters");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : additionalParameters.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
            }
            if (createOTAUpdateRequest.getTags() != null) {
                List<Tag> tags = createOTAUpdateRequest.getTags();
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
