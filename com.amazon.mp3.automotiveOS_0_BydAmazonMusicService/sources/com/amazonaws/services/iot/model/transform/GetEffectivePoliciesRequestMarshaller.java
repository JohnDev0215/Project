package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetEffectivePoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class GetEffectivePoliciesRequestMarshaller implements Marshaller<Request<GetEffectivePoliciesRequest>, GetEffectivePoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetEffectivePoliciesRequest> marshall(GetEffectivePoliciesRequest getEffectivePoliciesRequest) {
        if (getEffectivePoliciesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetEffectivePoliciesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getEffectivePoliciesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        if (getEffectivePoliciesRequest.getThingName() != null) {
            defaultRequest.addParameter("thingName", StringUtils.fromString(getEffectivePoliciesRequest.getThingName()));
        }
        defaultRequest.setResourcePath("/effective-policies");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (getEffectivePoliciesRequest.getPrincipal() != null) {
                String principal = getEffectivePoliciesRequest.getPrincipal();
                jsonWriter.name("principal");
                jsonWriter.value(principal);
            }
            if (getEffectivePoliciesRequest.getCognitoIdentityPoolId() != null) {
                String cognitoIdentityPoolId = getEffectivePoliciesRequest.getCognitoIdentityPoolId();
                jsonWriter.name("cognitoIdentityPoolId");
                jsonWriter.value(cognitoIdentityPoolId);
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
