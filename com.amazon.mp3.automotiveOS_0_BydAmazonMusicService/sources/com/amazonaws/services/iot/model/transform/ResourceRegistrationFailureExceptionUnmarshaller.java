package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.ResourceRegistrationFailureException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class ResourceRegistrationFailureExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ResourceRegistrationFailureExceptionUnmarshaller() {
        super(ResourceRegistrationFailureException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ResourceRegistrationFailureException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ResourceRegistrationFailureException resourceRegistrationFailureException = (ResourceRegistrationFailureException) super.unmarshall(jsonErrorResponse);
        resourceRegistrationFailureException.setErrorCode("ResourceRegistrationFailureException");
        return resourceRegistrationFailureException;
    }
}
