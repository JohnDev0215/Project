package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetectMitigationActionsTaskTarget;
import com.amazonaws.services.iot.model.StartDetectMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.ViolationEventOccurrenceRange;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StartDetectMitigationActionsTaskRequestMarshaller implements Marshaller<Request<StartDetectMitigationActionsTaskRequest>, StartDetectMitigationActionsTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StartDetectMitigationActionsTaskRequest> marshall(StartDetectMitigationActionsTaskRequest startDetectMitigationActionsTaskRequest) {
        if (startDetectMitigationActionsTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(StartDetectMitigationActionsTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(startDetectMitigationActionsTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/detect/mitigationactions/tasks/{taskId}".replace("{taskId}", startDetectMitigationActionsTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(startDetectMitigationActionsTaskRequest.getTaskId())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (startDetectMitigationActionsTaskRequest.getTarget() != null) {
                DetectMitigationActionsTaskTarget target = startDetectMitigationActionsTaskRequest.getTarget();
                jsonWriter.name("target");
                DetectMitigationActionsTaskTargetJsonMarshaller.getInstance().marshall(target, jsonWriter);
            }
            if (startDetectMitigationActionsTaskRequest.getActions() != null) {
                List<String> actions = startDetectMitigationActionsTaskRequest.getActions();
                jsonWriter.name("actions");
                jsonWriter.beginArray();
                for (String str : actions) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (startDetectMitigationActionsTaskRequest.getViolationEventOccurrenceRange() != null) {
                ViolationEventOccurrenceRange violationEventOccurrenceRange = startDetectMitigationActionsTaskRequest.getViolationEventOccurrenceRange();
                jsonWriter.name("violationEventOccurrenceRange");
                ViolationEventOccurrenceRangeJsonMarshaller.getInstance().marshall(violationEventOccurrenceRange, jsonWriter);
            }
            if (startDetectMitigationActionsTaskRequest.getIncludeOnlyActiveViolations() != null) {
                Boolean includeOnlyActiveViolations = startDetectMitigationActionsTaskRequest.getIncludeOnlyActiveViolations();
                jsonWriter.name("includeOnlyActiveViolations");
                jsonWriter.value(includeOnlyActiveViolations.booleanValue());
            }
            if (startDetectMitigationActionsTaskRequest.getIncludeSuppressedAlerts() != null) {
                Boolean includeSuppressedAlerts = startDetectMitigationActionsTaskRequest.getIncludeSuppressedAlerts();
                jsonWriter.name("includeSuppressedAlerts");
                jsonWriter.value(includeSuppressedAlerts.booleanValue());
            }
            if (startDetectMitigationActionsTaskRequest.getClientRequestToken() != null) {
                String clientRequestToken = startDetectMitigationActionsTaskRequest.getClientRequestToken();
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
