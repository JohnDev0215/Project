package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AuthorizerConfig;
import com.amazonaws.services.iot.model.CreateDomainConfigurationRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDomainConfigurationRequestMarshaller implements Marshaller<Request<CreateDomainConfigurationRequest>, CreateDomainConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateDomainConfigurationRequest> marshall(CreateDomainConfigurationRequest createDomainConfigurationRequest) {
        if (createDomainConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateDomainConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createDomainConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/domainConfigurations/{domainConfigurationName}".replace("{domainConfigurationName}", createDomainConfigurationRequest.getDomainConfigurationName() == null ? "" : StringUtils.fromString(createDomainConfigurationRequest.getDomainConfigurationName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createDomainConfigurationRequest.getDomainName() != null) {
                String domainName = createDomainConfigurationRequest.getDomainName();
                jsonWriter.name("domainName");
                jsonWriter.value(domainName);
            }
            if (createDomainConfigurationRequest.getServerCertificateArns() != null) {
                List<String> serverCertificateArns = createDomainConfigurationRequest.getServerCertificateArns();
                jsonWriter.name("serverCertificateArns");
                jsonWriter.beginArray();
                for (String str : serverCertificateArns) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createDomainConfigurationRequest.getValidationCertificateArn() != null) {
                String validationCertificateArn = createDomainConfigurationRequest.getValidationCertificateArn();
                jsonWriter.name("validationCertificateArn");
                jsonWriter.value(validationCertificateArn);
            }
            if (createDomainConfigurationRequest.getAuthorizerConfig() != null) {
                AuthorizerConfig authorizerConfig = createDomainConfigurationRequest.getAuthorizerConfig();
                jsonWriter.name("authorizerConfig");
                AuthorizerConfigJsonMarshaller.getInstance().marshall(authorizerConfig, jsonWriter);
            }
            if (createDomainConfigurationRequest.getServiceType() != null) {
                String serviceType = createDomainConfigurationRequest.getServiceType();
                jsonWriter.name("serviceType");
                jsonWriter.value(serviceType);
            }
            if (createDomainConfigurationRequest.getTags() != null) {
                List<Tag> tags = createDomainConfigurationRequest.getTags();
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
