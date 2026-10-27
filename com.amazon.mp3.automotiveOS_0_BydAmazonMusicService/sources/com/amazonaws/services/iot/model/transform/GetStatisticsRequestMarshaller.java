package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetStatisticsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class GetStatisticsRequestMarshaller implements Marshaller<Request<GetStatisticsRequest>, GetStatisticsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetStatisticsRequest> marshall(GetStatisticsRequest getStatisticsRequest) {
        if (getStatisticsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetStatisticsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getStatisticsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/indices/statistics");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (getStatisticsRequest.getIndexName() != null) {
                String indexName = getStatisticsRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (getStatisticsRequest.getQueryString() != null) {
                String queryString = getStatisticsRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (getStatisticsRequest.getAggregationField() != null) {
                String aggregationField = getStatisticsRequest.getAggregationField();
                jsonWriter.name("aggregationField");
                jsonWriter.value(aggregationField);
            }
            if (getStatisticsRequest.getQueryVersion() != null) {
                String queryVersion = getStatisticsRequest.getQueryVersion();
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
