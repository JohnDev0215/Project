package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.BucketsAggregationType;
import com.amazonaws.services.iot.model.GetBucketsAggregationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class GetBucketsAggregationRequestMarshaller implements Marshaller<Request<GetBucketsAggregationRequest>, GetBucketsAggregationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetBucketsAggregationRequest> marshall(GetBucketsAggregationRequest getBucketsAggregationRequest) {
        if (getBucketsAggregationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetBucketsAggregationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getBucketsAggregationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/indices/buckets");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (getBucketsAggregationRequest.getIndexName() != null) {
                String indexName = getBucketsAggregationRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (getBucketsAggregationRequest.getQueryString() != null) {
                String queryString = getBucketsAggregationRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (getBucketsAggregationRequest.getAggregationField() != null) {
                String aggregationField = getBucketsAggregationRequest.getAggregationField();
                jsonWriter.name("aggregationField");
                jsonWriter.value(aggregationField);
            }
            if (getBucketsAggregationRequest.getQueryVersion() != null) {
                String queryVersion = getBucketsAggregationRequest.getQueryVersion();
                jsonWriter.name("queryVersion");
                jsonWriter.value(queryVersion);
            }
            if (getBucketsAggregationRequest.getBucketsAggregationType() != null) {
                BucketsAggregationType bucketsAggregationType = getBucketsAggregationRequest.getBucketsAggregationType();
                jsonWriter.name("bucketsAggregationType");
                BucketsAggregationTypeJsonMarshaller.getInstance().marshall(bucketsAggregationType, jsonWriter);
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
