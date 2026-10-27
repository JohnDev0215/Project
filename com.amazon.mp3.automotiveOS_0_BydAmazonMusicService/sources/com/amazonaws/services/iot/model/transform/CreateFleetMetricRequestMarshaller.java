package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AggregationType;
import com.amazonaws.services.iot.model.CreateFleetMetricRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateFleetMetricRequestMarshaller implements Marshaller<Request<CreateFleetMetricRequest>, CreateFleetMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateFleetMetricRequest> marshall(CreateFleetMetricRequest createFleetMetricRequest) {
        if (createFleetMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateFleetMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createFleetMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PUT);
        defaultRequest.setResourcePath("/fleet-metric/{metricName}".replace("{metricName}", createFleetMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(createFleetMetricRequest.getMetricName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createFleetMetricRequest.getQueryString() != null) {
                String queryString = createFleetMetricRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (createFleetMetricRequest.getAggregationType() != null) {
                AggregationType aggregationType = createFleetMetricRequest.getAggregationType();
                jsonWriter.name("aggregationType");
                AggregationTypeJsonMarshaller.getInstance().marshall(aggregationType, jsonWriter);
            }
            if (createFleetMetricRequest.getPeriod() != null) {
                Integer period = createFleetMetricRequest.getPeriod();
                jsonWriter.name("period");
                jsonWriter.value(period);
            }
            if (createFleetMetricRequest.getAggregationField() != null) {
                String aggregationField = createFleetMetricRequest.getAggregationField();
                jsonWriter.name("aggregationField");
                jsonWriter.value(aggregationField);
            }
            if (createFleetMetricRequest.getDescription() != null) {
                String description = createFleetMetricRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createFleetMetricRequest.getQueryVersion() != null) {
                String queryVersion = createFleetMetricRequest.getQueryVersion();
                jsonWriter.name("queryVersion");
                jsonWriter.value(queryVersion);
            }
            if (createFleetMetricRequest.getIndexName() != null) {
                String indexName = createFleetMetricRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (createFleetMetricRequest.getUnit() != null) {
                String unit = createFleetMetricRequest.getUnit();
                jsonWriter.name("unit");
                jsonWriter.value(unit);
            }
            if (createFleetMetricRequest.getTags() != null) {
                List<Tag> tags = createFleetMetricRequest.getTags();
                jsonWriter.name("tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
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
