package com.amazonaws.services.iot.model.transform;

import androidx.autofill.HintConstants;
import com.amazonaws.services.iot.model.MqttContext;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MqttContextJsonUnmarshaller implements Unmarshaller<MqttContext, JsonUnmarshallerContext> {
    private static MqttContextJsonUnmarshaller instance;

    MqttContextJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MqttContext unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MqttContext mqttContext = new MqttContext();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals(HintConstants.AUTOFILL_HINT_USERNAME)) {
                mqttContext.setUsername(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("password")) {
                mqttContext.setPassword(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("clientId")) {
                mqttContext.setClientId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return mqttContext;
    }

    public static MqttContextJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MqttContextJsonUnmarshaller();
        }
        return instance;
    }
}
