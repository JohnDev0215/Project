package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RemoveThingFromThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveThingFromThingGroupRequestMarshaller implements Marshaller<Request<RemoveThingFromThingGroupRequest>, RemoveThingFromThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RemoveThingFromThingGroupRequest> marshall(RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest) {
        if (removeThingFromThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(RemoveThingFromThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(removeThingFromThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/thing-groups/removeThingFromThingGroup");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (removeThingFromThingGroupRequest.getThingGroupName() != null) {
                String thingGroupName = removeThingFromThingGroupRequest.getThingGroupName();
                jsonWriter.name("thingGroupName");
                jsonWriter.value(thingGroupName);
            }
            if (removeThingFromThingGroupRequest.getThingGroupArn() != null) {
                String thingGroupArn = removeThingFromThingGroupRequest.getThingGroupArn();
                jsonWriter.name("thingGroupArn");
                jsonWriter.value(thingGroupArn);
            }
            if (removeThingFromThingGroupRequest.getThingName() != null) {
                String thingName = removeThingFromThingGroupRequest.getThingName();
                jsonWriter.name("thingName");
                jsonWriter.value(thingName);
            }
            if (removeThingFromThingGroupRequest.getThingArn() != null) {
                String thingArn = removeThingFromThingGroupRequest.getThingArn();
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
