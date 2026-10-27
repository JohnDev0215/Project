package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.CreateSecurityProfileRequest;
import com.amazonaws.services.iot.model.MetricToRetain;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateSecurityProfileRequestMarshaller implements Marshaller<Request<CreateSecurityProfileRequest>, CreateSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateSecurityProfileRequest> marshall(CreateSecurityProfileRequest createSecurityProfileRequest) {
        if (createSecurityProfileRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateSecurityProfileRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createSecurityProfileRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/security-profiles/{securityProfileName}".replace("{securityProfileName}", createSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(createSecurityProfileRequest.getSecurityProfileName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createSecurityProfileRequest.getSecurityProfileDescription() != null) {
                String securityProfileDescription = createSecurityProfileRequest.getSecurityProfileDescription();
                jsonWriter.name("securityProfileDescription");
                jsonWriter.value(securityProfileDescription);
            }
            if (createSecurityProfileRequest.getBehaviors() != null) {
                List<Behavior> behaviors = createSecurityProfileRequest.getBehaviors();
                jsonWriter.name("behaviors");
                jsonWriter.beginArray();
                for (Behavior behavior : behaviors) {
                    if (behavior != null) {
                        BehaviorJsonMarshaller.getInstance().marshall(behavior, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createSecurityProfileRequest.getAlertTargets() != null) {
                Map<String, AlertTarget> alertTargets = createSecurityProfileRequest.getAlertTargets();
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
            if (createSecurityProfileRequest.getAdditionalMetricsToRetain() != null) {
                List<String> additionalMetricsToRetain = createSecurityProfileRequest.getAdditionalMetricsToRetain();
                jsonWriter.name("additionalMetricsToRetain");
                jsonWriter.beginArray();
                for (String str : additionalMetricsToRetain) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createSecurityProfileRequest.getAdditionalMetricsToRetainV2() != null) {
                List<MetricToRetain> additionalMetricsToRetainV2 = createSecurityProfileRequest.getAdditionalMetricsToRetainV2();
                jsonWriter.name("additionalMetricsToRetainV2");
                jsonWriter.beginArray();
                for (MetricToRetain metricToRetain : additionalMetricsToRetainV2) {
                    if (metricToRetain != null) {
                        MetricToRetainJsonMarshaller.getInstance().marshall(metricToRetain, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createSecurityProfileRequest.getTags() != null) {
                List<Tag> tags = createSecurityProfileRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
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
