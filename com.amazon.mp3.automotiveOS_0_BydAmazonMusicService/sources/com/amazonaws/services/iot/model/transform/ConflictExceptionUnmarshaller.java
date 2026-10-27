package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.ConflictException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class ConflictExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ConflictExceptionUnmarshaller() {
        super(ConflictException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ConflictException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ConflictException conflictException = (ConflictException) super.unmarshall(jsonErrorResponse);
        conflictException.setErrorCode("ConflictException");
        return conflictException;
    }
}
