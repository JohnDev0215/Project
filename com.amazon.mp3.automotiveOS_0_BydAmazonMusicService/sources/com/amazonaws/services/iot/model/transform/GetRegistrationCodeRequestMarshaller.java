package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetRegistrationCodeRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class GetRegistrationCodeRequestMarshaller implements Marshaller<Request<GetRegistrationCodeRequest>, GetRegistrationCodeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetRegistrationCodeRequest> marshall(GetRegistrationCodeRequest getRegistrationCodeRequest) {
        if (getRegistrationCodeRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetRegistrationCodeRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getRegistrationCodeRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        defaultRequest.setResourcePath("/registrationcode");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
