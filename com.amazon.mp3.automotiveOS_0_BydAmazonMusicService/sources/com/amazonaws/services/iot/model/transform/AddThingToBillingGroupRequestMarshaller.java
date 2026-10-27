package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AddThingToBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class AddThingToBillingGroupRequestMarshaller implements Marshaller<Request<AddThingToBillingGroupRequest>, AddThingToBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AddThingToBillingGroupRequest> marshall(AddThingToBillingGroupRequest addThingToBillingGroupRequest) {
        if (addThingToBillingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AddThingToBillingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(addThingToBillingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/billing-groups/addThingToBillingGroup");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (addThingToBillingGroupRequest.getBillingGroupName() != null) {
                String billingGroupName = addThingToBillingGroupRequest.getBillingGroupName();
                jsonWriter.name("billingGroupName");
                jsonWriter.value(billingGroupName);
            }
            if (addThingToBillingGroupRequest.getBillingGroupArn() != null) {
                String billingGroupArn = addThingToBillingGroupRequest.getBillingGroupArn();
                jsonWriter.name("billingGroupArn");
                jsonWriter.value(billingGroupArn);
            }
            if (addThingToBillingGroupRequest.getThingName() != null) {
                String thingName = addThingToBillingGroupRequest.getThingName();
                jsonWriter.name("thingName");
                jsonWriter.value(thingName);
            }
            if (addThingToBillingGroupRequest.getThingArn() != null) {
                String thingArn = addThingToBillingGroupRequest.getThingArn();
                jsonWriter.name("thingArn");
                jsonWriter.value(thingArn);
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
