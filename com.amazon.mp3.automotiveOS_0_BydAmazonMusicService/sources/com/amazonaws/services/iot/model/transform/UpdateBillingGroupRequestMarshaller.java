package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.BillingGroupProperties;
import com.amazonaws.services.iot.model.UpdateBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateBillingGroupRequestMarshaller implements Marshaller<Request<UpdateBillingGroupRequest>, UpdateBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateBillingGroupRequest> marshall(UpdateBillingGroupRequest updateBillingGroupRequest) {
        if (updateBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/billing-groups/{billingGroupName}".replace("{billingGroupName}", updateBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(updateBillingGroupRequest.getBillingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateBillingGroupRequest.getBillingGroupProperties() != null) {
                BillingGroupProperties billingGroupProperties = updateBillingGroupRequest.getBillingGroupProperties();
                jsonWriter.name("billingGroupProperties");
                BillingGroupPropertiesJsonMarshaller.getInstance().marshall(billingGroupProperties, jsonWriter);
            }
            if (updateBillingGroupRequest.getExpectedVersion() != null) {
                Long expectedVersion = updateBillingGroupRequest.getExpectedVersion();
                jsonWriter.name("expectedVersion");
                jsonWriter.value(expectedVersion);
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
