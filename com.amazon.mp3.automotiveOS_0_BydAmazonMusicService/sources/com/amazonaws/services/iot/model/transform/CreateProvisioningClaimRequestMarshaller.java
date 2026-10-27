package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateProvisioningClaimRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningClaimRequestMarshaller implements Marshaller<Request<CreateProvisioningClaimRequest>, CreateProvisioningClaimRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateProvisioningClaimRequest> marshall(CreateProvisioningClaimRequest createProvisioningClaimRequest) {
        if (createProvisioningClaimRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateProvisioningClaimRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createProvisioningClaimRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/provisioning-templates/{templateName}/provisioning-claim".replace("{templateName}", createProvisioningClaimRequest.getTemplateName() == null ? "" : StringUtils.fromString(createProvisioningClaimRequest.getTemplateName())));
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
