package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetIndexingConfigurationRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class GetIndexingConfigurationRequestMarshaller implements Marshaller<Request<GetIndexingConfigurationRequest>, GetIndexingConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetIndexingConfigurationRequest> marshall(GetIndexingConfigurationRequest getIndexingConfigurationRequest) {
        if (getIndexingConfigurationRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetIndexingConfigurationRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getIndexingConfigurationRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/indexing/config");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
