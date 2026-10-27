package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetBehaviorModelTrainingSummariesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class GetBehaviorModelTrainingSummariesRequestMarshaller implements Marshaller<Request<GetBehaviorModelTrainingSummariesRequest>, GetBehaviorModelTrainingSummariesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetBehaviorModelTrainingSummariesRequest> marshall(GetBehaviorModelTrainingSummariesRequest getBehaviorModelTrainingSummariesRequest) {
        if (getBehaviorModelTrainingSummariesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetBehaviorModelTrainingSummariesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getBehaviorModelTrainingSummariesRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (getBehaviorModelTrainingSummariesRequest.getSecurityProfileName() != null) {
            defaultRequest.addParameter("securityProfileName", StringUtils.fromString(getBehaviorModelTrainingSummariesRequest.getSecurityProfileName()));
        }
        if (getBehaviorModelTrainingSummariesRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(getBehaviorModelTrainingSummariesRequest.getMaxResults()));
        }
        if (getBehaviorModelTrainingSummariesRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(getBehaviorModelTrainingSummariesRequest.getNextToken()));
        }
        defaultRequest.setResourcePath("/behavior-model-training/summaries");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
