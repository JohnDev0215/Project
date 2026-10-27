package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeManagedJobTemplateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeManagedJobTemplateRequestMarshaller implements Marshaller<Request<DescribeManagedJobTemplateRequest>, DescribeManagedJobTemplateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeManagedJobTemplateRequest> marshall(DescribeManagedJobTemplateRequest describeManagedJobTemplateRequest) {
        if (describeManagedJobTemplateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeManagedJobTemplateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeManagedJobTemplateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/managed-job-templates/{templateName}".replace("{templateName}", describeManagedJobTemplateRequest.getTemplateName() == null ? "" : StringUtils.fromString(describeManagedJobTemplateRequest.getTemplateName()));
        if (describeManagedJobTemplateRequest.getTemplateVersion() != null) {
            defaultRequest.addParameter("templateVersion", StringUtils.fromString(describeManagedJobTemplateRequest.getTemplateVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
