package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateMitigationActionRequest;
import com.amazonaws.services.iot.model.MitigationActionParams;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateMitigationActionRequestMarshaller implements Marshaller<Request<CreateMitigationActionRequest>, CreateMitigationActionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateMitigationActionRequest> marshall(CreateMitigationActionRequest createMitigationActionRequest) {
        if (createMitigationActionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateMitigationActionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createMitigationActionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/mitigationactions/actions/{actionName}".replace("{actionName}", createMitigationActionRequest.getActionName() == null ? "" : StringUtils.fromString(createMitigationActionRequest.getActionName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createMitigationActionRequest.getRoleArn() != null) {
                String roleArn = createMitigationActionRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (createMitigationActionRequest.getActionParams() != null) {
                MitigationActionParams actionParams = createMitigationActionRequest.getActionParams();
                jsonWriter.name("actionParams");
                MitigationActionParamsJsonMarshaller.getInstance().marshall(actionParams, jsonWriter);
            }
            if (createMitigationActionRequest.getTags() != null) {
                List<Tag> tags = createMitigationActionRequest.getTags();
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
