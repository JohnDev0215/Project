package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuditSuppressionsRequest;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditSuppressionsRequestMarshaller implements Marshaller<Request<ListAuditSuppressionsRequest>, ListAuditSuppressionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuditSuppressionsRequest> marshall(ListAuditSuppressionsRequest listAuditSuppressionsRequest) {
        if (listAuditSuppressionsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListAuditSuppressionsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listAuditSuppressionsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/audit/suppressions/list");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (listAuditSuppressionsRequest.getCheckName() != null) {
                String checkName = listAuditSuppressionsRequest.getCheckName();
                jsonWriter.name("checkName");
                jsonWriter.value(checkName);
            }
            if (listAuditSuppressionsRequest.getResourceIdentifier() != null) {
                ResourceIdentifier resourceIdentifier = listAuditSuppressionsRequest.getResourceIdentifier();
                jsonWriter.name("resourceIdentifier");
                ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, jsonWriter);
            }
            if (listAuditSuppressionsRequest.getAscendingOrder() != null) {
                Boolean ascendingOrder = listAuditSuppressionsRequest.getAscendingOrder();
                jsonWriter.name("ascendingOrder");
                jsonWriter.value(ascendingOrder.booleanValue());
            }
            if (listAuditSuppressionsRequest.getNextToken() != null) {
                String nextToken = listAuditSuppressionsRequest.getNextToken();
                jsonWriter.name("nextToken");
                jsonWriter.value(nextToken);
            }
            if (listAuditSuppressionsRequest.getMaxResults() != null) {
                Integer maxResults = listAuditSuppressionsRequest.getMaxResults();
                jsonWriter.name("maxResults");
                jsonWriter.value(maxResults);
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
