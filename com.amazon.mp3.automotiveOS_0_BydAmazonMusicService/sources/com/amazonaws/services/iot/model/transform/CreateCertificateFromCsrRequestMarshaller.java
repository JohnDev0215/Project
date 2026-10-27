package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCertificateFromCsrRequestMarshaller implements Marshaller<Request<CreateCertificateFromCsrRequest>, CreateCertificateFromCsrRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateCertificateFromCsrRequest> marshall(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) {
        if (createCertificateFromCsrRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateCertificateFromCsrRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createCertificateFromCsrRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        if (createCertificateFromCsrRequest.getSetAsActive() != null) {
            defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(createCertificateFromCsrRequest.getSetAsActive()));
        }
        defaultRequest.setResourcePath("/certificates");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createCertificateFromCsrRequest.getCertificateSigningRequest() != null) {
                String certificateSigningRequest = createCertificateFromCsrRequest.getCertificateSigningRequest();
                jsonWriter.name("certificateSigningRequest");
                jsonWriter.value(certificateSigningRequest);
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
