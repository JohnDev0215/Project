package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.services.iot.model.UpdateCACertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateCACertificateRequestMarshaller implements Marshaller<Request<UpdateCACertificateRequest>, UpdateCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateCACertificateRequest> marshall(UpdateCACertificateRequest updateCACertificateRequest) {
        if (updateCACertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateCACertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateCACertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        String strReplace = "/cacertificate/{caCertificateId}".replace("{caCertificateId}", updateCACertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(updateCACertificateRequest.getCertificateId()));
        if (updateCACertificateRequest.getNewStatus() != null) {
            defaultRequest.addParameter("newStatus", StringUtils.fromString(updateCACertificateRequest.getNewStatus()));
        }
        if (updateCACertificateRequest.getNewAutoRegistrationStatus() != null) {
            defaultRequest.addParameter("newAutoRegistrationStatus", StringUtils.fromString(updateCACertificateRequest.getNewAutoRegistrationStatus()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateCACertificateRequest.getRegistrationConfig() != null) {
                RegistrationConfig registrationConfig = updateCACertificateRequest.getRegistrationConfig();
                jsonWriter.name("registrationConfig");
                RegistrationConfigJsonMarshaller.getInstance().marshall(registrationConfig, jsonWriter);
            }
            if (updateCACertificateRequest.getRemoveAutoRegistration() != null) {
                Boolean removeAutoRegistration = updateCACertificateRequest.getRemoveAutoRegistration();
                jsonWriter.name("removeAutoRegistration");
                jsonWriter.value(removeAutoRegistration.booleanValue());
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
