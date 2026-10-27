package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AuditMitigationActionsTaskTarget;
import com.amazonaws.services.iot.model.StartAuditMitigationActionsTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class StartAuditMitigationActionsTaskRequestMarshaller implements Marshaller<Request<StartAuditMitigationActionsTaskRequest>, StartAuditMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StartAuditMitigationActionsTaskRequest> marshall(StartAuditMitigationActionsTaskRequest startAuditMitigationActionsTaskRequest) {
        if (startAuditMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(StartAuditMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(startAuditMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/audit/mitigationactions/tasks/{taskId}".replace("{taskId}", startAuditMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(startAuditMitigationActionsTaskRequest.getTaskId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (startAuditMitigationActionsTaskRequest.getTarget() != null) {
                AuditMitigationActionsTaskTarget target = startAuditMitigationActionsTaskRequest.getTarget();
                jsonWriter.name("target");
                AuditMitigationActionsTaskTargetJsonMarshaller.getInstance().marshall(target, jsonWriter);
            }
            if (startAuditMitigationActionsTaskRequest.getAuditCheckToActionsMapping() != null) {
                Map<String, List<String>> auditCheckToActionsMapping = startAuditMitigationActionsTaskRequest.getAuditCheckToActionsMapping();
                jsonWriter.name("auditCheckToActionsMapping");
                jsonWriter.beginObject();
                for (Map.Entry<String, List<String>> entry : auditCheckToActionsMapping.entrySet()) {
                    List<String> value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.beginArray();
                        for (String str : value) {
                            if (str != null) {
                                jsonWriter.value(str);
                            }
                        }
                        jsonWriter.endArray();
                    }
                }
                jsonWriter.endObject();
            }
            if (startAuditMitigationActionsTaskRequest.getClientRequestToken() != null) {
                String clientRequestToken = startAuditMitigationActionsTaskRequest.getClientRequestToken();
                jsonWriter.name("clientRequestToken");
                jsonWriter.value(clientRequestToken);
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
