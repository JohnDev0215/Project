package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegisterCACertificateRequest;
import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCACertificateRequestMarshaller implements Marshaller<Request<RegisterCACertificateRequest>, RegisterCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RegisterCACertificateRequest> marshall(RegisterCACertificateRequest registerCACertificateRequest) {
        if (registerCACertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(RegisterCACertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(registerCACertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        if (registerCACertificateRequest.getSetAsActive() != null) {
            defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(registerCACertificateRequest.getSetAsActive()));
        }
        if (registerCACertificateRequest.getAllowAutoRegistration() != null) {
            defaultRequest.addParameter("allowAutoRegistration", StringUtils.fromBoolean(registerCACertificateRequest.getAllowAutoRegistration()));
        }
        defaultRequest.setResourcePath("/cacertificate");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (registerCACertificateRequest.getCaCertificate() != null) {
                String caCertificate = registerCACertificateRequest.getCaCertificate();
                jsonWriter.name("caCertificate");
                jsonWriter.value(caCertificate);
            }
            if (registerCACertificateRequest.getVerificationCertificate() != null) {
                String verificationCertificate = registerCACertificateRequest.getVerificationCertificate();
                jsonWriter.name("verificationCertificate");
                jsonWriter.value(verificationCertificate);
            }
            if (registerCACertificateRequest.getRegistrationConfig() != null) {
                RegistrationConfig registrationConfig = registerCACertificateRequest.getRegistrationConfig();
                jsonWriter.name("registrationConfig");
                RegistrationConfigJsonMarshaller.getInstance().marshall(registrationConfig, jsonWriter);
            }
            if (registerCACertificateRequest.getTags() != null) {
                List<Tag> tags = registerCACertificateRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (registerCACertificateRequest.getCertificateMode() != null) {
                String certificateMode = registerCACertificateRequest.getCertificateMode();
                jsonWriter.name("certificateMode");
                jsonWriter.value(certificateMode);
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
