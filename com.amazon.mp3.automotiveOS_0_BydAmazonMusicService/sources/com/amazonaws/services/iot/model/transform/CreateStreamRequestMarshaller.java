package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateStreamRequest;
import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateStreamRequestMarshaller implements Marshaller<Request<CreateStreamRequest>, CreateStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateStreamRequest> marshall(CreateStreamRequest createStreamRequest) {
        if (createStreamRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateStreamRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createStreamRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/streams/{streamId}".replace("{streamId}", createStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(createStreamRequest.getStreamId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createStreamRequest.getDescription() != null) {
                String description = createStreamRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createStreamRequest.getFiles() != null) {
                List<StreamFile> files = createStreamRequest.getFiles();
                jsonWriter.name("files");
                jsonWriter.beginArray();
                for (StreamFile streamFile : files) {
                    if (streamFile != null) {
                        StreamFileJsonMarshaller.getInstance().marshall(streamFile, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createStreamRequest.getRoleArn() != null) {
                String roleArn = createStreamRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (createStreamRequest.getTags() != null) {
                List<Tag> tags = createStreamRequest.getTags();
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
