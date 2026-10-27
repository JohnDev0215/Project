package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingTypeRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingTypeRequestMarshaller implements Marshaller<Request<DescribeThingTypeRequest>, DescribeThingTypeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingTypeRequest> marshall(DescribeThingTypeRequest describeThingTypeRequest) {
        if (describeThingTypeRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingTypeRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeThingTypeRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/thing-types/{thingTypeName}".replace("{thingTypeName}", describeThingTypeRequest.getThingTypeName() == null ? "" : StringUtils.fromString(describeThingTypeRequest.getThingTypeName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
