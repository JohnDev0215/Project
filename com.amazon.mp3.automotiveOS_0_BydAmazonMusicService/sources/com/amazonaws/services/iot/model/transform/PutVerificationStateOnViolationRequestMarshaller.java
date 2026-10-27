package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.PutVerificationStateOnViolationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class PutVerificationStateOnViolationRequestMarshaller implements Marshaller<Request<PutVerificationStateOnViolationRequest>, PutVerificationStateOnViolationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PutVerificationStateOnViolationRequest> marshall(PutVerificationStateOnViolationRequest putVerificationStateOnViolationRequest) {
        if (putVerificationStateOnViolationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(PutVerificationStateOnViolationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(putVerificationStateOnViolationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/violations/verification-state/{violationId}".replace("{violationId}", putVerificationStateOnViolationRequest.getViolationId() == null ? "" : StringUtils.fromString(putVerificationStateOnViolationRequest.getViolationId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (putVerificationStateOnViolationRequest.getVerificationState() != null) {
                String verificationState = putVerificationStateOnViolationRequest.getVerificationState();
                jsonWriter.name("verificationState");
                jsonWriter.value(verificationState);
            }
            if (putVerificationStateOnViolationRequest.getVerificationStateDescription() != null) {
                String verificationStateDescription = putVerificationStateOnViolationRequest.getVerificationStateDescription();
                jsonWriter.name("verificationStateDescription");
                jsonWriter.value(verificationStateDescription);
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
