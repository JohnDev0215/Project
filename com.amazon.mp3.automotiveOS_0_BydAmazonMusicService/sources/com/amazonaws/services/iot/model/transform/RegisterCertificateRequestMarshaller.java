package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegisterCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateRequestMarshaller implements Marshaller<Request<RegisterCertificateRequest>, RegisterCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RegisterCertificateRequest> marshall(RegisterCertificateRequest registerCertificateRequest) {
        if (registerCertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(RegisterCertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(registerCertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        if (registerCertificateRequest.getSetAsActive() != null) {
            defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(registerCertificateRequest.getSetAsActive()));
        }
        defaultRequest.setResourcePath("/certificate/register");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (registerCertificateRequest.getCertificatePem() != null) {
                String certificatePem = registerCertificateRequest.getCertificatePem();
                jsonWriter.name("certificatePem");
                jsonWriter.value(certificatePem);
            }
            if (registerCertificateRequest.getCaCertificatePem() != null) {
                String caCertificatePem = registerCertificateRequest.getCaCertificatePem();
                jsonWriter.name("caCertificatePem");
                jsonWriter.value(caCertificatePem);
            }
            if (registerCertificateRequest.getStatus() != null) {
                String status = registerCertificateRequest.getStatus();
                jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
                jsonWriter.value(status);
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
