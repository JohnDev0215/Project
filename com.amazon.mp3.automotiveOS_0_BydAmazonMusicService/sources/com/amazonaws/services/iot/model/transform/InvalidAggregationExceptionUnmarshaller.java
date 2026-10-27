package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.InvalidAggregationException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class InvalidAggregationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidAggregationExceptionUnmarshaller() {
        super(InvalidAggregationException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidAggregationException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidAggregationException invalidAggregationException = (InvalidAggregationException) super.unmarshall(jsonErrorResponse);
        invalidAggregationException.setErrorCode("InvalidAggregationException");
        return invalidAggregationException;
    }
}
