package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeBillingGroupRequestMarshaller implements Marshaller<Request<DescribeBillingGroupRequest>, DescribeBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeBillingGroupRequest> marshall(DescribeBillingGroupRequest describeBillingGroupRequest) {
        if (describeBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/billing-groups/{billingGroupName}".replace("{billingGroupName}", describeBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(describeBillingGroupRequest.getBillingGroupName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
