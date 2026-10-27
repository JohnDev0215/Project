package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.MetricToRetain;
import com.amazonaws.services.iot.model.UpdateSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateSecurityProfileRequestMarshaller implements Marshaller<Request<UpdateSecurityProfileRequest>, UpdateSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateSecurityProfileRequest> marshall(UpdateSecurityProfileRequest updateSecurityProfileRequest) {
        if (updateSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        String strReplace = "/security-profiles/{securityProfileName}".replace("{securityProfileName}", updateSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(updateSecurityProfileRequest.getSecurityProfileName()));
        if (updateSecurityProfileRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(updateSecurityProfileRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateSecurityProfileRequest.getSecurityProfileDescription() != null) {
                String securityProfileDescription = updateSecurityProfileRequest.getSecurityProfileDescription();
                jsonWriter.name("securityProfileDescription");
                jsonWriter.value(securityProfileDescription);
            }
            if (updateSecurityProfileRequest.getBehaviors() != null) {
                List<Behavior> behaviors = updateSecurityProfileRequest.getBehaviors();
                jsonWriter.name("behaviors");
                jsonWriter.beginArray();
                for (Behavior behavior : behaviors) {
                    if (behavior != null) {
                        BehaviorJsonMarshaller.getInstance().marshall(behavior, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateSecurityProfileRequest.getAlertTargets() != null) {
                Map<String, AlertTarget> alertTargets = updateSecurityProfileRequest.getAlertTargets();
                jsonWriter.name("alertTargets");
                jsonWriter.beginObject();
                for (Map.Entry<String, AlertTarget> entry : alertTargets.entrySet()) {
                    AlertTarget value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        AlertTargetJsonMarshaller.getInstance().marshall(value, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (updateSecurityProfileRequest.getAdditionalMetricsToRetain() != null) {
                List<String> additionalMetricsToRetain = updateSecurityProfileRequest.getAdditionalMetricsToRetain();
                jsonWriter.name("additionalMetricsToRetain");
                jsonWriter.beginArray();
                for (String str : additionalMetricsToRetain) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateSecurityProfileRequest.getAdditionalMetricsToRetainV2() != null) {
                List<MetricToRetain> additionalMetricsToRetainV2 = updateSecurityProfileRequest.getAdditionalMetricsToRetainV2();
                jsonWriter.name("additionalMetricsToRetainV2");
                jsonWriter.beginArray();
                for (MetricToRetain metricToRetain : additionalMetricsToRetainV2) {
                    if (metricToRetain != null) {
                        MetricToRetainJsonMarshaller.getInstance().marshall(metricToRetain, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateSecurityProfileRequest.getDeleteBehaviors() != null) {
                Boolean deleteBehaviors = updateSecurityProfileRequest.getDeleteBehaviors();
                jsonWriter.name("deleteBehaviors");
                jsonWriter.value(deleteBehaviors.booleanValue());
            }
            if (updateSecurityProfileRequest.getDeleteAlertTargets() != null) {
                Boolean deleteAlertTargets = updateSecurityProfileRequest.getDeleteAlertTargets();
                jsonWriter.name("deleteAlertTargets");
                jsonWriter.value(deleteAlertTargets.booleanValue());
            }
            if (updateSecurityProfileRequest.getDeleteAdditionalMetricsToRetain() != null) {
                Boolean deleteAdditionalMetricsToRetain = updateSecurityProfileRequest.getDeleteAdditionalMetricsToRetain();
                jsonWriter.name("deleteAdditionalMetricsToRetain");
                jsonWriter.value(deleteAdditionalMetricsToRetain.booleanValue());
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
