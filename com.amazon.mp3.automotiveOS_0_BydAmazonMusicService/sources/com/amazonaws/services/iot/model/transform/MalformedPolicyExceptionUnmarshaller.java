package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.MalformedPolicyException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class MalformedPolicyExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public MalformedPolicyExceptionUnmarshaller() {
        super(MalformedPolicyException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("MalformedPolicyException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        MalformedPolicyException malformedPolicyException = (MalformedPolicyException) super.unmarshall(jsonErrorResponse);
        malformedPolicyException.setErrorCode("MalformedPolicyException");
        return malformedPolicyException;
    }
}
