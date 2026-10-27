package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateDynamicThingGroupRequest;
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
public class CreateDynamicThingGroupRequestMarshaller implements Marshaller<Request<CreateDynamicThingGroupRequest>, CreateDynamicThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateDynamicThingGroupRequest> marshall(CreateDynamicThingGroupRequest createDynamicThingGroupRequest) {
        if (createDynamicThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateDynamicThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createDynamicThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/dynamic-thing-groups/{thingGroupName}".replace("{thingGroupName}", createDynamicThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(createDynamicThingGroupRequest.getThingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createDynamicThingGroupRequest.getThingGroupProperties() != null) {
                ThingGroupProperties thingGroupProperties = createDynamicThingGroupRequest.getThingGroupProperties();
                jsonWriter.name("thingGroupProperties");
                ThingGroupPropertiesJsonMarshaller.getInstance().marshall(thingGroupProperties, jsonWriter);
            }
            if (createDynamicThingGroupRequest.getIndexName() != null) {
                String indexName = createDynamicThingGroupRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (createDynamicThingGroupRequest.getQueryString() != null) {
                String queryString = createDynamicThingGroupRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (createDynamicThingGroupRequest.getQueryVersion() != null) {
                String queryVersion = createDynamicThingGroupRequest.getQueryVersion();
                jsonWriter.name("queryVersion");
                jsonWriter.value(queryVersion);
            }
            if (createDynamicThingGroupRequest.getTags() != null) {
                List<Tag> tags = createDynamicThingGroupRequest.getTags();
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
