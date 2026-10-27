package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForJobRequestMarshaller implements Marshaller<Request<ListJobExecutionsForJobRequest>, ListJobExecutionsForJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobExecutionsForJobRequest> marshall(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) {
        if (listJobExecutionsForJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListJobExecutionsForJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listJobExecutionsForJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/jobs/{jobId}/things".replace("{jobId}", listJobExecutionsForJobRequest.getJobId() == null ? "" : StringUtils.fromString(listJobExecutionsForJobRequest.getJobId()));
        if (listJobExecutionsForJobRequest.getStatus() != null) {
            defaultRequest.addParameter(NotificationCompat.CATEGORY_STATUS, StringUtils.fromString(listJobExecutionsForJobRequest.getStatus()));
        }
        if (listJobExecutionsForJobRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobExecutionsForJobRequest.getMaxResults()));
        }
        if (listJobExecutionsForJobRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobExecutionsForJobRequest.getNextToken()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
