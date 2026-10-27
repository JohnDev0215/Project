package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class AttachPolicyRequestMarshaller implements Marshaller<Request<AttachPolicyRequest>, AttachPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachPolicyRequest> marshall(AttachPolicyRequest attachPolicyRequest) {
        if (attachPolicyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AttachPolicyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(attachPolicyRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/target-policies/{policyName}".replace("{policyName}", attachPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(attachPolicyRequest.getPolicyName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (attachPolicyRequest.getTarget() != null) {
                String target = attachPolicyRequest.getTarget();
                jsonWriter.name("target");
                jsonWriter.value(target);
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
