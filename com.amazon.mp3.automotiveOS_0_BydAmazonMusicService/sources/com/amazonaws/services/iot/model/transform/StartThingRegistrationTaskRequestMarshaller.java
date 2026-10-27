package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.StartThingRegistrationTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class StartThingRegistrationTaskRequestMarshaller implements Marshaller<Request<StartThingRegistrationTaskRequest>, StartThingRegistrationTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StartThingRegistrationTaskRequest> marshall(StartThingRegistrationTaskRequest startThingRegistrationTaskRequest) {
        if (startThingRegistrationTaskRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(StartThingRegistrationTaskRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(startThingRegistrationTaskRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/thing-registration-tasks");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (startThingRegistrationTaskRequest.getTemplateBody() != null) {
                String templateBody = startThingRegistrationTaskRequest.getTemplateBody();
                jsonWriter.name("templateBody");
                jsonWriter.value(templateBody);
            }
            if (startThingRegistrationTaskRequest.getInputFileBucket() != null) {
                String inputFileBucket = startThingRegistrationTaskRequest.getInputFileBucket();
                jsonWriter.name("inputFileBucket");
                jsonWriter.value(inputFileBucket);
            }
            if (startThingRegistrationTaskRequest.getInputFileKey() != null) {
                String inputFileKey = startThingRegistrationTaskRequest.getInputFileKey();
                jsonWriter.name("inputFileKey");
                jsonWriter.value(inputFileKey);
            }
            if (startThingRegistrationTaskRequest.getRoleArn() != null) {
                String roleArn = startThingRegistrationTaskRequest.getRoleArn();
                jsonWriter.name("roleArn");
                jsonWriter.value(roleArn);
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
