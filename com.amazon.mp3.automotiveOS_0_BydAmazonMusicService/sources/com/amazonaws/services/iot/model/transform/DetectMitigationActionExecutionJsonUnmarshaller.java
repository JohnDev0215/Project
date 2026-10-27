package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.DetectMitigationActionExecution;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionExecutionJsonUnmarshaller implements Unmarshaller<DetectMitigationActionExecution, JsonUnmarshallerContext> {
    private static DetectMitigationActionExecutionJsonUnmarshaller instance;

    DetectMitigationActionExecutionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetectMitigationActionExecution unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DetectMitigationActionExecution detectMitigationActionExecution = new DetectMitigationActionExecution();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskId")) {
                detectMitigationActionExecution.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("violationId")) {
                detectMitigationActionExecution.setViolationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionName")) {
                detectMitigationActionExecution.setActionName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingName")) {
                detectMitigationActionExecution.setThingName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("executionStartDate")) {
                detectMitigationActionExecution.setExecutionStartDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("executionEndDate")) {
                detectMitigationActionExecution.setExecutionEndDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                detectMitigationActionExecution.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorCode")) {
                detectMitigationActionExecution.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("message")) {
                detectMitigationActionExecution.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return detectMitigationActionExecution;
    }

    public static DetectMitigationActionExecutionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionExecutionJsonUnmarshaller();
        }
        return instance;
    }
}
