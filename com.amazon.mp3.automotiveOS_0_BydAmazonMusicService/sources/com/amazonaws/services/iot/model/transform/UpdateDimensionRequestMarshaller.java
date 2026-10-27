package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateDimensionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDimensionRequestMarshaller implements Marshaller<Request<UpdateDimensionRequest>, UpdateDimensionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateDimensionRequest> marshall(UpdateDimensionRequest updateDimensionRequest) {
        if (updateDimensionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateDimensionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateDimensionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/dimensions/{name}".replace("{name}", updateDimensionRequest.getName() == null ? "" : StringUtils.fromString(updateDimensionRequest.getName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateDimensionRequest.getStringValues() != null) {
                List<String> stringValues = updateDimensionRequest.getStringValues();
                jsonWriter.name("stringValues");
                jsonWriter.beginArray();
                for (String str : stringValues) {
                    if (str != null) {
                        jsonWriter.value(str);
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
