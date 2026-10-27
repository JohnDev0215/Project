package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class LogTargetJsonUnmarshaller implements Unmarshaller<LogTarget, JsonUnmarshallerContext> {
    private static LogTargetJsonUnmarshaller instance;

    LogTargetJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LogTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LogTarget logTarget = new LogTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("targetType")) {
                logTarget.setTargetType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("targetName")) {
                logTarget.setTargetName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return logTarget;
    }

    public static LogTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetJsonUnmarshaller();
        }
        return instance;
    }
}
