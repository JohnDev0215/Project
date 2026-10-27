package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ThingGroupProperties;
import com.amazonaws.services.iot.model.UpdateThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupRequestMarshaller implements Marshaller<Request<UpdateThingGroupRequest>, UpdateThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingGroupRequest> marshall(UpdateThingGroupRequest updateThingGroupRequest) {
        if (updateThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/thing-groups/{thingGroupName}".replace("{thingGroupName}", updateThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(updateThingGroupRequest.getThingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateThingGroupRequest.getThingGroupProperties() != null) {
                ThingGroupProperties thingGroupProperties = updateThingGroupRequest.getThingGroupProperties();
                jsonWriter.name("thingGroupProperties");
                ThingGroupPropertiesJsonMarshaller.getInstance().marshall(thingGroupProperties, jsonWriter);
            }
            if (updateThingGroupRequest.getExpectedVersion() != null) {
                Long expectedVersion = updateThingGroupRequest.getExpectedVersion();
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
