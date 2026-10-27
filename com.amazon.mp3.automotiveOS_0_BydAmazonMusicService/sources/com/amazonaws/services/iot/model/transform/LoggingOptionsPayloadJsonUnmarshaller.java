package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LoggingOptionsPayload;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class LoggingOptionsPayloadJsonUnmarshaller implements Unmarshaller<LoggingOptionsPayload, JsonUnmarshallerContext> {
    private static LoggingOptionsPayloadJsonUnmarshaller instance;

    LoggingOptionsPayloadJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LoggingOptionsPayload unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LoggingOptionsPayload loggingOptionsPayload = new LoggingOptionsPayload();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                loggingOptionsPayload.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("logLevel")) {
                loggingOptionsPayload.setLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return loggingOptionsPayload;
    }

    public static LoggingOptionsPayloadJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LoggingOptionsPayloadJsonUnmarshaller();
        }
        return instance;
    }
}
