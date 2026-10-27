package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupsForThingRequestMarshaller implements Marshaller<Request<UpdateThingGroupsForThingRequest>, UpdateThingGroupsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingGroupsForThingRequest> marshall(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) {
        if (updateThingGroupsForThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingGroupsForThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateThingGroupsForThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/thing-groups/updateThingGroupsForThing");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateThingGroupsForThingRequest.getThingName() != null) {
                String thingName = updateThingGroupsForThingRequest.getThingName();
                jsonWriter.name("thingName");
                jsonWriter.value(thingName);
            }
            if (updateThingGroupsForThingRequest.getThingGroupsToAdd() != null) {
                List<String> thingGroupsToAdd = updateThingGroupsForThingRequest.getThingGroupsToAdd();
                jsonWriter.name("thingGroupsToAdd");
                jsonWriter.beginArray();
                for (String str : thingGroupsToAdd) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateThingGroupsForThingRequest.getThingGroupsToRemove() != null) {
                List<String> thingGroupsToRemove = updateThingGroupsForThingRequest.getThingGroupsToRemove();
                jsonWriter.name("thingGroupsToRemove");
                jsonWriter.beginArray();
                for (String str2 : thingGroupsToRemove) {
                    if (str2 != null) {
                        jsonWriter.value(str2);
                    }
                }
                jsonWriter.endArray();
            }
            if (updateThingGroupsForThingRequest.getOverrideDynamicGroups() != null) {
                Boolean overrideDynamicGroups = updateThingGroupsForThingRequest.getOverrideDynamicGroups();
                jsonWriter.name("overrideDynamicGroups");
                jsonWriter.value(overrideDynamicGroups.booleanValue());
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
