package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.EnableIoTLoggingParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class EnableIoTLoggingParamsJsonUnmarshaller implements Unmarshaller<EnableIoTLoggingParams, JsonUnmarshallerContext> {
    private static EnableIoTLoggingParamsJsonUnmarshaller instance;

    EnableIoTLoggingParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public EnableIoTLoggingParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        EnableIoTLoggingParams enableIoTLoggingParams = new EnableIoTLoggingParams();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArnForLogging")) {
                enableIoTLoggingParams.setRoleArnForLogging(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("logLevel")) {
                enableIoTLoggingParams.setLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return enableIoTLoggingParams;
    }

    public static EnableIoTLoggingParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new EnableIoTLoggingParamsJsonUnmarshaller();
        }
        return instance;
    }
}
