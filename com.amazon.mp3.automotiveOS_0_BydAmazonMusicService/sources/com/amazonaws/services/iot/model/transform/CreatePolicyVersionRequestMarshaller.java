package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreatePolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class CreatePolicyVersionRequestMarshaller implements Marshaller<Request<CreatePolicyVersionRequest>, CreatePolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreatePolicyVersionRequest> marshall(CreatePolicyVersionRequest createPolicyVersionRequest) {
        if (createPolicyVersionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreatePolicyVersionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createPolicyVersionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/policies/{policyName}/version".replace("{policyName}", createPolicyVersionRequest.getPolicyName() == null ? "" : StringUtils.fromString(createPolicyVersionRequest.getPolicyName()));
        if (createPolicyVersionRequest.getSetAsDefault() != null) {
            defaultRequest.addParameter("setAsDefault", StringUtils.fromBoolean(createPolicyVersionRequest.getSetAsDefault()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createPolicyVersionRequest.getPolicyDocument() != null) {
                String policyDocument = createPolicyVersionRequest.getPolicyDocument();
                jsonWriter.name("policyDocument");
                jsonWriter.value(policyDocument);
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
