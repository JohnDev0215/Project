package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingGroupRequestMarshaller implements Marshaller<Request<DescribeThingGroupRequest>, DescribeThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingGroupRequest> marshall(DescribeThingGroupRequest describeThingGroupRequest) {
        if (describeThingGroupRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingGroupRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(describeThingGroupRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/thing-groups/{thingGroupName}".replace("{thingGroupName}", describeThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(describeThingGroupRequest.getThingGroupName())));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
