package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeDimensionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDimensionRequestMarshaller implements Marshaller<Request<DescribeDimensionRequest>, DescribeDimensionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeDimensionRequest> marshall(DescribeDimensionRequest describeDimensionRequest) {
        if (describeDimensionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeDimensionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeDimensionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/dimensions/{name}".replace("{name}", describeDimensionRequest.getName() == null ? "" : StringUtils.fromString(describeDimensionRequest.getName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
