package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegisterCertificateWithoutCARequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterCertificateWithoutCARequestMarshaller implements Marshaller<Request<RegisterCertificateWithoutCARequest>, RegisterCertificateWithoutCARequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RegisterCertificateWithoutCARequest> marshall(RegisterCertificateWithoutCARequest registerCertificateWithoutCARequest) {
        if (registerCertificateWithoutCARequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(RegisterCertificateWithoutCARequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(registerCertificateWithoutCARequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/certificate/register-no-ca");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (registerCertificateWithoutCARequest.getCertificatePem() != null) {
                String certificatePem = registerCertificateWithoutCARequest.getCertificatePem();
                jsonWriter.name("certificatePem");
                jsonWriter.value(certificatePem);
            }
            if (registerCertificateWithoutCARequest.getStatus() != null) {
                String status = registerCertificateWithoutCARequest.getStatus();
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
