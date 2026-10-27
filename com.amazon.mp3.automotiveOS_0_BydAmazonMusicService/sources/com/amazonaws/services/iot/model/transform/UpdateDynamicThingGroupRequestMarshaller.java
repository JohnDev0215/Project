package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ThingGroupProperties;
import com.amazonaws.services.iot.model.UpdateDynamicThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDynamicThingGroupRequestMarshaller implements Marshaller<Request<UpdateDynamicThingGroupRequest>, UpdateDynamicThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateDynamicThingGroupRequest> marshall(UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest) {
        if (updateDynamicThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateDynamicThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateDynamicThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/dynamic-thing-groups/{thingGroupName}".replace("{thingGroupName}", updateDynamicThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(updateDynamicThingGroupRequest.getThingGroupName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateDynamicThingGroupRequest.getThingGroupProperties() != null) {
                ThingGroupProperties thingGroupProperties = updateDynamicThingGroupRequest.getThingGroupProperties();
                jsonWriter.name("thingGroupProperties");
                ThingGroupPropertiesJsonMarshaller.getInstance().marshall(thingGroupProperties, jsonWriter);
            }
            if (updateDynamicThingGroupRequest.getExpectedVersion() != null) {
                Long expectedVersion = updateDynamicThingGroupRequest.getExpectedVersion();
                jsonWriter.name("expectedVersion");
                jsonWriter.value(expectedVersion);
            }
            if (updateDynamicThingGroupRequest.getIndexName() != null) {
                String indexName = updateDynamicThingGroupRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (updateDynamicThingGroupRequest.getQueryString() != null) {
                String queryString = updateDynamicThingGroupRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (updateDynamicThingGroupRequest.getQueryVersion() != null) {
                String queryVersion = updateDynamicThingGroupRequest.getQueryVersion();
                jsonWriter.name("queryVersion");
                jsonWriter.value(queryVersion);
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
