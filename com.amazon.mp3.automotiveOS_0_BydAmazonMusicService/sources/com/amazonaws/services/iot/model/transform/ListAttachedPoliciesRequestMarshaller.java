package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAttachedPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class ListAttachedPoliciesRequestMarshaller implements Marshaller<Request<ListAttachedPoliciesRequest>, ListAttachedPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAttachedPoliciesRequest> marshall(ListAttachedPoliciesRequest listAttachedPoliciesRequest) {
        if (listAttachedPoliciesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListAttachedPoliciesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listAttachedPoliciesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/attached-policies/{target}".replace("{target}", listAttachedPoliciesRequest.getTarget() == null ? "" : StringUtils.fromString(listAttachedPoliciesRequest.getTarget()));
        if (listAttachedPoliciesRequest.getRecursive() != null) {
            defaultRequest.addParameter("recursive", StringUtils.fromBoolean(listAttachedPoliciesRequest.getRecursive()));
        }
        if (listAttachedPoliciesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listAttachedPoliciesRequest.getMarker()));
        }
        if (listAttachedPoliciesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listAttachedPoliciesRequest.getPageSize()));
        }
        defaultRequest.setResourcePath(strReplace);
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, "0");
        defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
