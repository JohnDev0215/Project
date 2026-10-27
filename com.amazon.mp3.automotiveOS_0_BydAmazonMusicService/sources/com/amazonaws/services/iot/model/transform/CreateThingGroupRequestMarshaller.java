package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateThingGroupRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.services.iot.model.ThingGroupProperties;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingGroupRequestMarshaller implements Marshaller<Request<CreateThingGroupRequest>, CreateThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateThingGroupRequest> marshall(CreateThingGroupRequest createThingGroupRequest) {
        if (createThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/thing-groups/{thingGroupName}".replace("{thingGroupName}", createThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(createThingGroupRequest.getThingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createThingGroupRequest.getParentGroupName() != null) {
                String parentGroupName = createThingGroupRequest.getParentGroupName();
                jsonWriter.name("parentGroupName");
                jsonWriter.value(parentGroupName);
            }
            if (createThingGroupRequest.getThingGroupProperties() != null) {
                ThingGroupProperties thingGroupProperties = createThingGroupRequest.getThingGroupProperties();
                jsonWriter.name("thingGroupProperties");
                ThingGroupPropertiesJsonMarshaller.getInstance().marshall(thingGroupProperties, jsonWriter);
            }
            if (createThingGroupRequest.getTags() != null) {
                List<Tag> tags = createThingGroupRequest.getTags();
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
