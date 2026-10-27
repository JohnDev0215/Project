package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListRoleAliasesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListRoleAliasesRequestMarshaller implements Marshaller<Request<ListRoleAliasesRequest>, ListRoleAliasesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListRoleAliasesRequest> marshall(ListRoleAliasesRequest listRoleAliasesRequest) {
        if (listRoleAliasesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListRoleAliasesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listRoleAliasesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listRoleAliasesRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listRoleAliasesRequest.getPageSize()));
        }
        if (listRoleAliasesRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listRoleAliasesRequest.getMarker()));
        }
        if (listRoleAliasesRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listRoleAliasesRequest.getAscendingOrder()));
        }
        defaultRequest.setResourcePath("/role-aliases");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
