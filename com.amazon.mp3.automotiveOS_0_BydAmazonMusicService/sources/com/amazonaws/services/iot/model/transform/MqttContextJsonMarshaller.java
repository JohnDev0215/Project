package com.amazonaws.services.iot.model.transform;

import androidx.autofill.HintConstants;
import com.amazonaws.services.iot.model.MqttContext;
import com.amazonaws.util.json.AwsJsonWriter;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
class MqttContextJsonMarshaller {
    private static MqttContextJsonMarshaller instance;

    MqttContextJsonMarshaller() {
    }

    public void marshall(MqttContext mqttContext, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (mqttContext.getUsername() != null) {
            String username = mqttContext.getUsername();
            awsJsonWriter.name(HintConstants.AUTOFILL_HINT_USERNAME);
            awsJsonWriter.value(username);
        }
        if (mqttContext.getPassword() != null) {
            ByteBuffer password = mqttContext.getPassword();
            awsJsonWriter.name("password");
            awsJsonWriter.value(password);
        }
        if (mqttContext.getClientId() != null) {
            String clientId = mqttContext.getClientId();
            awsJsonWriter.name("clientId");
            awsJsonWriter.value(clientId);
        }
        awsJsonWriter.endObject();
    }

    public static MqttContextJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MqttContextJsonMarshaller();
        }
        return instance;
    }
}
