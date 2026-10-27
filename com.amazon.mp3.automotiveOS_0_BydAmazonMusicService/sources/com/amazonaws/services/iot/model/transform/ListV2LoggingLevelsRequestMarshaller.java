package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListV2LoggingLevelsRequestMarshaller implements Marshaller<Request<ListV2LoggingLevelsRequest>, ListV2LoggingLevelsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListV2LoggingLevelsRequest> marshall(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) {
        if (listV2LoggingLevelsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListV2LoggingLevelsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listV2LoggingLevelsRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listV2LoggingLevelsRequest.getTargetType() != null) {
            defaultRequest.addParameter("targetType", StringUtils.fromString(listV2LoggingLevelsRequest.getTargetType()));
        }
        if (listV2LoggingLevelsRequest.getNextToken() != null) {
            defaultRequest.addParameter("nextToken", StringUtils.fromString(listV2LoggingLevelsRequest.getNextToken()));
        }
        if (listV2LoggingLevelsRequest.getMaxResults() != null) {
            defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listV2LoggingLevelsRequest.getMaxResults()));
        }
        defaultRequest.setResourcePath("/v2LoggingLevel");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
