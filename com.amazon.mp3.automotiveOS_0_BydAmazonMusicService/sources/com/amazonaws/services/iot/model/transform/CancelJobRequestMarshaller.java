package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class CancelJobRequestMarshaller implements Marshaller<Request<CancelJobRequest>, CancelJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelJobRequest> marshall(CancelJobRequest cancelJobRequest) {
        if (cancelJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        String strReplace = "/jobs/{jobId}/cancel".replace("{jobId}", cancelJobRequest.getJobId() == null ? "" : StringUtils.fromString(cancelJobRequest.getJobId()));
        if (cancelJobRequest.getForce() != null) {
            defaultRequest.addParameter("force", StringUtils.fromBoolean(cancelJobRequest.getForce()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (cancelJobRequest.getReasonCode() != null) {
                String reasonCode = cancelJobRequest.getReasonCode();
                jsonWriter.name("reasonCode");
                jsonWriter.value(reasonCode);
            }
            if (cancelJobRequest.getComment() != null) {
                String comment = cancelJobRequest.getComment();
                jsonWriter.name("comment");
                jsonWriter.value(comment);
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
