package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateRoleAliasRequestMarshaller implements Marshaller<Request<UpdateRoleAliasRequest>, UpdateRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateRoleAliasRequest> marshall(UpdateRoleAliasRequest updateRoleAliasRequest) {
        if (updateRoleAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateRoleAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateRoleAliasRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/role-aliases/{roleAlias}".replace("{roleAlias}", updateRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(updateRoleAliasRequest.getRoleAlias())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateRoleAliasRequest.getRoleArn() != null) {
                String roleArn = updateRoleAliasRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (updateRoleAliasRequest.getCredentialDurationSeconds() != null) {
                Integer credentialDurationSeconds = updateRoleAliasRequest.getCredentialDurationSeconds();
                jsonWriter.name("credentialDurationSeconds");
                jsonWriter.value(credentialDurationSeconds);
            }
            jsonWriter.endObject();
            jsonWriter.close();
            String string = stringWriter.toString();
            byte[] bytes = string.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(string));
            defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, Integer.toString(bytes.length));
            if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
                defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
            }
            return defaultRequest;
        } catch (Throwable th) {
            throw new AmazonClientException("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
