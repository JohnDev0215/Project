package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForThingRequestMarshaller implements Marshaller<Request<ListJobExecutionsForThingRequest>, ListJobExecutionsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobExecutionsForThingRequest> marshall(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) {
        if (listJobExecutionsForThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListJobExecutionsForThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listJobExecutionsForThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        String strReplace = "/things/{thingName}/jobs".replace("{thingName}", listJobExecutionsForThingRequest.getThingName() == null ? "" : StringUtils.fromString(listJobExecutionsForThingRequest.getThingName()));
        if (listJobExecutionsForThingRequest.getStatus() != null) {
            defaultRequest.addParameter(NotificationCompat.CATEGORY_STATUS, StringUtils.fromString(listJobExecutionsForThingRequest.getStatus()));
        }
        if (listJobExecutionsForThingRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(listJobExecutionsForThingRequest.getNamespaceId()));
        }
        if (listJobExecutionsForThingRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobExecutionsForThingRequest.getMaxResults()));
        }
        if (listJobExecutionsForThingRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobExecutionsForThingRequest.getNextToken()));
        }
        if (listJobExecutionsForThingRequest.getJobId() != null) {
            defaultRequest.addParameter("jobId", StringUtils.fromString(listJobExecutionsForThingRequest.getJobId()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
