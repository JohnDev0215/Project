package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.services.iot.model.UpdateThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingRequestMarshaller implements Marshaller<Request<UpdateThingRequest>, UpdateThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingRequest> marshall(UpdateThingRequest updateThingRequest) {
        if (updateThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/things/{thingName}".replace("{thingName}", updateThingRequest.getThingName() == null ? "" : StringUtils.fromString(updateThingRequest.getThingName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateThingRequest.getThingTypeName() != null) {
                String thingTypeName = updateThingRequest.getThingTypeName();
                jsonWriter.name("thingTypeName");
                jsonWriter.value(thingTypeName);
            }
            if (updateThingRequest.getAttributePayload() != null) {
                AttributePayload attributePayload = updateThingRequest.getAttributePayload();
                jsonWriter.name("attributePayload");
                AttributePayloadJsonMarshaller.getInstance().marshall(attributePayload, jsonWriter);
            }
            if (updateThingRequest.getExpectedVersion() != null) {
                Long expectedVersion = updateThingRequest.getExpectedVersion();
                jsonWriter.name("expectedVersion");
                jsonWriter.value(expectedVersion);
            }
            if (updateThingRequest.getRemoveThingType() != null) {
                Boolean removeThingType = updateThingRequest.getRemoveThingType();
                jsonWriter.name("removeThingType");
                jsonWriter.value(removeThingType.booleanValue());
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
