package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SearchIndexRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class SearchIndexRequestMarshaller implements Marshaller<Request<SearchIndexRequest>, SearchIndexRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SearchIndexRequest> marshall(SearchIndexRequest searchIndexRequest) {
        if (searchIndexRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SearchIndexRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(searchIndexRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/indices/search");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (searchIndexRequest.getIndexName() != null) {
                String indexName = searchIndexRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (searchIndexRequest.getQueryString() != null) {
                String queryString = searchIndexRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (searchIndexRequest.getNextToken() != null) {
                String nextToken = searchIndexRequest.getNextToken();
                jsonWriter.name("nextToken");
                jsonWriter.value(nextToken);
            }
            if (searchIndexRequest.getMaxResults() != null) {
                Integer maxResults = searchIndexRequest.getMaxResults();
                jsonWriter.name("maxResults");
                jsonWriter.value(maxResults);
            }
            if (searchIndexRequest.getQueryVersion() != null) {
                String queryVersion = searchIndexRequest.getQueryVersion();
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
