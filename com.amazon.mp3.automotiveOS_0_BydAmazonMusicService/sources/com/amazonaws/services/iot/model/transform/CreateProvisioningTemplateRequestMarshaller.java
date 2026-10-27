package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateProvisioningTemplateRequest;
import com.amazonaws.services.iot.model.ProvisioningHook;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningTemplateRequestMarshaller implements Marshaller<Request<CreateProvisioningTemplateRequest>, CreateProvisioningTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateProvisioningTemplateRequest> marshall(CreateProvisioningTemplateRequest createProvisioningTemplateRequest) {
        if (createProvisioningTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateProvisioningTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createProvisioningTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/provisioning-templates");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createProvisioningTemplateRequest.getTemplateName() != null) {
                String templateName = createProvisioningTemplateRequest.getTemplateName();
                jsonWriter.name("templateName");
                jsonWriter.value(templateName);
            }
            if (createProvisioningTemplateRequest.getDescription() != null) {
                String description = createProvisioningTemplateRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createProvisioningTemplateRequest.getTemplateBody() != null) {
                String templateBody = createProvisioningTemplateRequest.getTemplateBody();
                jsonWriter.name("templateBody");
                jsonWriter.value(templateBody);
            }
            if (createProvisioningTemplateRequest.getEnabled() != null) {
                Boolean enabled = createProvisioningTemplateRequest.getEnabled();
                jsonWriter.name("enabled");
                jsonWriter.value(enabled.booleanValue());
            }
            if (createProvisioningTemplateRequest.getProvisioningRoleArn() != null) {
                String provisioningRoleArn = createProvisioningTemplateRequest.getProvisioningRoleArn();
                jsonWriter.name("provisioningRoleArn");
                jsonWriter.value(provisioningRoleArn);
            }
            if (createProvisioningTemplateRequest.getPreProvisioningHook() != null) {
                ProvisioningHook preProvisioningHook = createProvisioningTemplateRequest.getPreProvisioningHook();
                jsonWriter.name("preProvisioningHook");
                ProvisioningHookJsonMarshaller.getInstance().marshall(preProvisioningHook, jsonWriter);
            }
            if (createProvisioningTemplateRequest.getTags() != null) {
                List<Tag> tags = createProvisioningTemplateRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createProvisioningTemplateRequest.getType() != null) {
                String type = createProvisioningTemplateRequest.getType();
                jsonWriter.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                jsonWriter.value(type);
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
