package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.services.iot.model.UpdateStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateStreamRequestMarshaller implements Marshaller<Request<UpdateStreamRequest>, UpdateStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateStreamRequest> marshall(UpdateStreamRequest updateStreamRequest) {
        if (updateStreamRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateStreamRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateStreamRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/streams/{streamId}".replace("{streamId}", updateStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(updateStreamRequest.getStreamId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateStreamRequest.getDescription() != null) {
                String description = updateStreamRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (updateStreamRequest.getFiles() != null) {
                List<StreamFile> files = updateStreamRequest.getFiles();
                jsonWriter.name("files");
                jsonWriter.beginArray();
                for (StreamFile streamFile : files) {
                    if (streamFile != null) {
                        StreamFileJsonMarshaller.getInstance().marshall(streamFile, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateStreamRequest.getRoleArn() != null) {
                String roleArn = updateStreamRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
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
