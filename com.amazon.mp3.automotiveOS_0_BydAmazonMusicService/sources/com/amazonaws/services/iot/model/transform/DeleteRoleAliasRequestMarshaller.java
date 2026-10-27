package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteRoleAliasRequestMarshaller implements Marshaller<Request<DeleteRoleAliasRequest>, DeleteRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteRoleAliasRequest> marshall(DeleteRoleAliasRequest deleteRoleAliasRequest) {
        if (deleteRoleAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteRoleAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteRoleAliasRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/role-aliases/{roleAlias}".replace("{roleAlias}", deleteRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(deleteRoleAliasRequest.getRoleAlias())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
