package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class PublishRequestMarshaller implements Marshaller<Request<PublishRequest>, PublishRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PublishRequest> marshall(PublishRequest publishRequest) {
        if (publishRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(PublishRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(publishRequest, "AWSIotData");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/topics/{topic}".replace("{topic}", publishRequest.getTopic() == null ? "" : StringUtils.fromString(publishRequest.getTopic()));
        if (publishRequest.getQos() != null) {
            defaultRequest.addParameter("qos", StringUtils.fromInteger(publishRequest.getQos()));
        }
        if (publishRequest.getRetain() != null) {
            defaultRequest.addParameter("retain", StringUtils.fromBoolean(publishRequest.getRetain()));
        }
        defaultRequest.setResourcePath(strReplace);
        defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, Integer.toString(publishRequest.getPayload().remaining()));
        defaultRequest.setContent(BinaryUtils.toStream(publishRequest.getPayload()));
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
