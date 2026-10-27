package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CancelJobExecutionRequestMarshaller implements Marshaller<Request<CancelJobExecutionRequest>, CancelJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelJobExecutionRequest> marshall(CancelJobExecutionRequest cancelJobExecutionRequest) {
        if (cancelJobExecutionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CancelJobExecutionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(cancelJobExecutionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        String strReplace = "/things/{thingName}/jobs/{jobId}/cancel".replace("{jobId}", cancelJobExecutionRequest.getJobId() == null ? "" : StringUtils.fromString(cancelJobExecutionRequest.getJobId())).replace("{thingName}", cancelJobExecutionRequest.getThingName() != null ? StringUtils.fromString(cancelJobExecutionRequest.getThingName()) : "");
        if (cancelJobExecutionRequest.getForce() != null) {
            defaultRequest.addParameter("force", StringUtils.fromBoolean(cancelJobExecutionRequest.getForce()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (cancelJobExecutionRequest.getExpectedVersion() != null) {
                Long expectedVersion = cancelJobExecutionRequest.getExpectedVersion();
                jsonWriter.name("expectedVersion");
                jsonWriter.value(expectedVersion);
            }
            if (cancelJobExecutionRequest.getStatusDetails() != null) {
                Map<String, String> statusDetails = cancelJobExecutionRequest.getStatusDetails();
                jsonWriter.name("statusDetails");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : statusDetails.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
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
