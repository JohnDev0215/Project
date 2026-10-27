package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ProvisioningHook;
import com.amazonaws.services.iot.model.UpdateProvisioningTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateProvisioningTemplateRequestMarshaller implements Marshaller<Request<UpdateProvisioningTemplateRequest>, UpdateProvisioningTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateProvisioningTemplateRequest> marshall(UpdateProvisioningTemplateRequest updateProvisioningTemplateRequest) {
        if (updateProvisioningTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateProvisioningTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateProvisioningTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}".replace("{templateName}", updateProvisioningTemplateRequest.getTemplateName() == null ? "" : StringUtils.fromString(updateProvisioningTemplateRequest.getTemplateName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateProvisioningTemplateRequest.getDescription() != null) {
                String description = updateProvisioningTemplateRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (updateProvisioningTemplateRequest.getEnabled() != null) {
                Boolean enabled = updateProvisioningTemplateRequest.getEnabled();
                jsonWriter.name("enabled");
                jsonWriter.value(enabled.booleanValue());
            }
            if (updateProvisioningTemplateRequest.getDefaultVersionId() != null) {
                Integer defaultVersionId = updateProvisioningTemplateRequest.getDefaultVersionId();
                jsonWriter.name("defaultVersionId");
                jsonWriter.value(defaultVersionId);
            }
            if (updateProvisioningTemplateRequest.getProvisioningRoleArn() != null) {
                String provisioningRoleArn = updateProvisioningTemplateRequest.getProvisioningRoleArn();
                jsonWriter.name("provisioningRoleArn");
                jsonWriter.value(provisioningRoleArn);
            }
            if (updateProvisioningTemplateRequest.getPreProvisioningHook() != null) {
                ProvisioningHook preProvisioningHook = updateProvisioningTemplateRequest.getPreProvisioningHook();
                jsonWriter.name("preProvisioningHook");
                ProvisioningHookJsonMarshaller.getInstance().marshall(preProvisioningHook, jsonWriter);
            }
            if (updateProvisioningTemplateRequest.getRemovePreProvisioningHook() != null) {
                Boolean removePreProvisioningHook = updateProvisioningTemplateRequest.getRemovePreProvisioningHook();
                jsonWriter.name("removePreProvisioningHook");
                jsonWriter.value(removePreProvisioningHook.booleanValue());
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
