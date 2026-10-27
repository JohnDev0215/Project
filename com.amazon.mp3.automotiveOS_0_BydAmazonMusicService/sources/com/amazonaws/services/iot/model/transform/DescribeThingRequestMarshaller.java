package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingRequestMarshaller implements Marshaller<Request<DescribeThingRequest>, DescribeThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingRequest> marshall(DescribeThingRequest describeThingRequest) {
        if (describeThingRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeThingRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/things/{thingName}".replace("{thingName}", describeThingRequest.getThingName() == null ? "" : StringUtils.fromString(describeThingRequest.getThingName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
