package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeRequest;
import com.amazonaws.transform.Marshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteRegistrationCodeRequestMarshaller implements Marshaller<Request<DeleteRegistrationCodeRequest>, DeleteRegistrationCodeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteRegistrationCodeRequest> marshall(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) {
        if (deleteRegistrationCodeRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteRegistrationCodeRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteRegistrationCodeRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        defaultRequest.setResourcePath("/registrationcode");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
