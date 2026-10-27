package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsInBillingGroupRequestMarshaller implements Marshaller<Request<ListThingsInBillingGroupRequest>, ListThingsInBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingsInBillingGroupRequest> marshall(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) {
        if (listThingsInBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListThingsInBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listThingsInBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/billing-groups/{billingGroupName}/things".replace("{billingGroupName}", listThingsInBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(listThingsInBillingGroupRequest.getBillingGroupName()));
        if (listThingsInBillingGroupRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingsInBillingGroupRequest.getNextToken()));
        }
        if (listThingsInBillingGroupRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingsInBillingGroupRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
