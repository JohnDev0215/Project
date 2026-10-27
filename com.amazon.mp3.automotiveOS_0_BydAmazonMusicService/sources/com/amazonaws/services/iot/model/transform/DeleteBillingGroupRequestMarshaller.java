package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteBillingGroupRequestMarshaller implements Marshaller<Request<DeleteBillingGroupRequest>, DeleteBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteBillingGroupRequest> marshall(DeleteBillingGroupRequest deleteBillingGroupRequest) {
        if (deleteBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/billing-groups/{billingGroupName}".replace("{billingGroupName}", deleteBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(deleteBillingGroupRequest.getBillingGroupName()));
        if (deleteBillingGroupRequest.getExpectedVersion() != null) {
            defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteBillingGroupRequest.getExpectedVersion()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
