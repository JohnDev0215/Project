package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.MitigationActionParams;
import com.amazonaws.services.iot.model.UpdateMitigationActionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateMitigationActionRequestMarshaller implements Marshaller<Request<UpdateMitigationActionRequest>, UpdateMitigationActionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateMitigationActionRequest> marshall(UpdateMitigationActionRequest updateMitigationActionRequest) {
        if (updateMitigationActionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateMitigationActionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateMitigationActionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/mitigationactions/actions/{actionName}".replace("{actionName}", updateMitigationActionRequest.getActionName() == null ? "" : StringUtils.fromString(updateMitigationActionRequest.getActionName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateMitigationActionRequest.getRoleArn() != null) {
                String roleArn = updateMitigationActionRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
            }
            if (updateMitigationActionRequest.getActionParams() != null) {
                MitigationActionParams actionParams = updateMitigationActionRequest.getActionParams();
                jsonWriter.name("actionParams");
                MitigationActionParamsJsonMarshaller.getInstance().marshall(actionParams, jsonWriter);
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
