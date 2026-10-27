package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.TransferCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class TransferCertificateRequestMarshaller implements Marshaller<Request<TransferCertificateRequest>, TransferCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<TransferCertificateRequest> marshall(TransferCertificateRequest transferCertificateRequest) {
        if (transferCertificateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(TransferCertificateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(transferCertificateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        String strReplace = "/transfer-certificate/{certificateId}".replace("{certificateId}", transferCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(transferCertificateRequest.getCertificateId()));
        if (transferCertificateRequest.getTargetAwsAccount() != null) {
            defaultRequest.addParameter("targetAwsAccount", StringUtils.fromString(transferCertificateRequest.getTargetAwsAccount()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (transferCertificateRequest.getTransferMessage() != null) {
                String transferMessage = transferCertificateRequest.getTransferMessage();
                jsonWriter.name("transferMessage");
                jsonWriter.value(transferMessage);
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
