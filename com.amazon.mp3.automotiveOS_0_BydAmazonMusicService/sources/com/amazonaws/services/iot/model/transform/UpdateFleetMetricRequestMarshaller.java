package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AggregationType;
import com.amazonaws.services.iot.model.UpdateFleetMetricRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateFleetMetricRequestMarshaller implements Marshaller<Request<UpdateFleetMetricRequest>, UpdateFleetMetricRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateFleetMetricRequest> marshall(UpdateFleetMetricRequest updateFleetMetricRequest) {
        if (updateFleetMetricRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateFleetMetricRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateFleetMetricRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/fleet-metric/{metricName}".replace("{metricName}", updateFleetMetricRequest.getMetricName() == null ? "" : StringUtils.fromString(updateFleetMetricRequest.getMetricName())));
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateFleetMetricRequest.getQueryString() != null) {
                String queryString = updateFleetMetricRequest.getQueryString();
                jsonWriter.name("queryString");
                jsonWriter.value(queryString);
            }
            if (updateFleetMetricRequest.getAggregationType() != null) {
                AggregationType aggregationType = updateFleetMetricRequest.getAggregationType();
                jsonWriter.name("aggregationType");
                AggregationTypeJsonMarshaller.getInstance().marshall(aggregationType, jsonWriter);
            }
            if (updateFleetMetricRequest.getPeriod() != null) {
                Integer period = updateFleetMetricRequest.getPeriod();
                jsonWriter.name("period");
                jsonWriter.value(period);
            }
            if (updateFleetMetricRequest.getAggregationField() != null) {
                String aggregationField = updateFleetMetricRequest.getAggregationField();
                jsonWriter.name("aggregationField");
                jsonWriter.value(aggregationField);
            }
            if (updateFleetMetricRequest.getDescription() != null) {
                String description = updateFleetMetricRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (updateFleetMetricRequest.getQueryVersion() != null) {
                String queryVersion = updateFleetMetricRequest.getQueryVersion();
                jsonWriter.name("queryVersion");
                jsonWriter.value(queryVersion);
            }
            if (updateFleetMetricRequest.getIndexName() != null) {
                String indexName = updateFleetMetricRequest.getIndexName();
                jsonWriter.name("indexName");
                jsonWriter.value(indexName);
            }
            if (updateFleetMetricRequest.getUnit() != null) {
                String unit = updateFleetMetricRequest.getUnit();
                jsonWriter.name("unit");
                jsonWriter.value(unit);
            }
            if (updateFleetMetricRequest.getExpectedVersion() != null) {
                Long expectedVersion = updateFleetMetricRequest.getExpectedVersion();
                jsonWriter.name("expectedVersion");
                jsonWriter.value(expectedVersion);
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
