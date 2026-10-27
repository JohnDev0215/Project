package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AlertTargetJsonUnmarshaller implements Unmarshaller<AlertTarget, JsonUnmarshallerContext> {
    private static AlertTargetJsonUnmarshaller instance;

    AlertTargetJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AlertTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AlertTarget alertTarget = new AlertTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("alertTargetArn")) {
                alertTarget.setAlertTargetArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                alertTarget.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return alertTarget;
    }

    public static AlertTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AlertTargetJsonUnmarshaller();
        }
        return instance;
    }
}
