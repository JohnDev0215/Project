package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.TaskAlreadyExistsException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class TaskAlreadyExistsExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public TaskAlreadyExistsExceptionUnmarshaller() {
        super(TaskAlreadyExistsException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("TaskAlreadyExistsException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        TaskAlreadyExistsException taskAlreadyExistsException = (TaskAlreadyExistsException) super.unmarshall(jsonErrorResponse);
        taskAlreadyExistsException.setErrorCode("TaskAlreadyExistsException");
        return taskAlreadyExistsException;
    }
}
