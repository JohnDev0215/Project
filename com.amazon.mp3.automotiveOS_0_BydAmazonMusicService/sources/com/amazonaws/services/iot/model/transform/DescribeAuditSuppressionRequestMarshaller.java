package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuditSuppressionRequest;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditSuppressionRequestMarshaller implements Marshaller<Request<DescribeAuditSuppressionRequest>, DescribeAuditSuppressionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuditSuppressionRequest> marshall(DescribeAuditSuppressionRequest describeAuditSuppressionRequest) {
        if (describeAuditSuppressionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuditSuppressionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeAuditSuppressionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/audit/suppressions/describe");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (describeAuditSuppressionRequest.getCheckName() != null) {
                String checkName = describeAuditSuppressionRequest.getCheckName();
                jsonWriter.name("checkName");
                jsonWriter.value(checkName);
            }
            if (describeAuditSuppressionRequest.getResourceIdentifier() != null) {
                ResourceIdentifier resourceIdentifier = describeAuditSuppressionRequest.getResourceIdentifier();
                jsonWriter.name("resourceIdentifier");
                ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, jsonWriter);
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
