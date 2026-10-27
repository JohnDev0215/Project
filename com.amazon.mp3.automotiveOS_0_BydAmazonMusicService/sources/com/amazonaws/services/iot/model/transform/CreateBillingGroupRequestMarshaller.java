package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.BillingGroupProperties;
import com.amazonaws.services.iot.model.CreateBillingGroupRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateBillingGroupRequestMarshaller implements Marshaller<Request<CreateBillingGroupRequest>, CreateBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateBillingGroupRequest> marshall(CreateBillingGroupRequest createBillingGroupRequest) {
        if (createBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/billing-groups/{billingGroupName}".replace("{billingGroupName}", createBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(createBillingGroupRequest.getBillingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createBillingGroupRequest.getBillingGroupProperties() != null) {
                BillingGroupProperties billingGroupProperties = createBillingGroupRequest.getBillingGroupProperties();
                jsonWriter.name("billingGroupProperties");
                BillingGroupPropertiesJsonMarshaller.getInstance().marshall(billingGroupProperties, jsonWriter);
            }
            if (createBillingGroupRequest.getTags() != null) {
                List<Tag> tags = createBillingGroupRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
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
