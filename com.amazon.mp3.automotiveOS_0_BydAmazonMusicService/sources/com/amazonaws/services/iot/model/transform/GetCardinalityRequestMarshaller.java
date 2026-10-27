package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetCardinalityRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class GetCardinalityRequestMarshaller implements Marshaller<Request<GetCardinalityRequest>, GetCardinalityRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetCardinalityRequest> marshall(GetCardinalityRequest getCardinalityRequest) {
        if (getCardinalityRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetCardinalityRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getCardinalityRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/indices/cardinality");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (getCardinalityRequest.getIndexName() != null) {
                String indexName = getCardinalityRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (getCardinalityRequest.getQueryString() != null) {
                String queryString = getCardinalityRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (getCardinalityRequest.getAggregationField() != null) {
                String aggregationField = getCardinalityRequest.getAggregationField();
                jsonWriter.name("aggregationField");
                jsonWriter.value(aggregationField);
            }
            if (getCardinalityRequest.getQueryVersion() != null) {
                String queryVersion = getCardinalityRequest.getQueryVersion();
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
