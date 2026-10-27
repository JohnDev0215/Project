package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AuthorizerConfig;
import com.amazonaws.services.iot.model.UpdateDomainConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDomainConfigurationRequestMarshaller implements Marshaller<Request<UpdateDomainConfigurationRequest>, UpdateDomainConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateDomainConfigurationRequest> marshall(UpdateDomainConfigurationRequest updateDomainConfigurationRequest) {
        if (updateDomainConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateDomainConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateDomainConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/domainConfigurations/{domainConfigurationName}".replace("{domainConfigurationName}", updateDomainConfigurationRequest.getDomainConfigurationName() == null ? "" : StringUtils.fromString(updateDomainConfigurationRequest.getDomainConfigurationName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateDomainConfigurationRequest.getAuthorizerConfig() != null) {
                AuthorizerConfig authorizerConfig = updateDomainConfigurationRequest.getAuthorizerConfig();
                jsonWriter.name("authorizerConfig");
                AuthorizerConfigJsonMarshaller.getInstance().marshall(authorizerConfig, jsonWriter);
            }
            if (updateDomainConfigurationRequest.getDomainConfigurationStatus() != null) {
                String domainConfigurationStatus = updateDomainConfigurationRequest.getDomainConfigurationStatus();
                jsonWriter.name("domainConfigurationStatus");
                jsonWriter.value(domainConfigurationStatus);
            }
            if (updateDomainConfigurationRequest.getRemoveAuthorizerConfig() != null) {
                Boolean removeAuthorizerConfig = updateDomainConfigurationRequest.getRemoveAuthorizerConfig();
                jsonWriter.name("removeAuthorizerConfig");
                jsonWriter.value(removeAuthorizerConfig.booleanValue());
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
