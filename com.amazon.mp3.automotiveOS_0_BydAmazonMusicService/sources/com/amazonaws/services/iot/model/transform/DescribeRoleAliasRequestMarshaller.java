package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeRoleAliasRequestMarshaller implements Marshaller<Request<DescribeRoleAliasRequest>, DescribeRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeRoleAliasRequest> marshall(DescribeRoleAliasRequest describeRoleAliasRequest) {
        if (describeRoleAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeRoleAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeRoleAliasRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/role-aliases/{roleAlias}".replace("{roleAlias}", describeRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(describeRoleAliasRequest.getRoleAlias())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
