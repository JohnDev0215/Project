package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeMitigationActionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeMitigationActionRequestMarshaller implements Marshaller<Request<DescribeMitigationActionRequest>, DescribeMitigationActionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeMitigationActionRequest> marshall(DescribeMitigationActionRequest describeMitigationActionRequest) {
        if (describeMitigationActionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeMitigationActionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeMitigationActionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/mitigationactions/actions/{actionName}".replace("{actionName}", describeMitigationActionRequest.getActionName() == null ? "" : StringUtils.fromString(describeMitigationActionRequest.getActionName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
