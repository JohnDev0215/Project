package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ProvisioningHook;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ProvisioningHookJsonUnmarshaller implements Unmarshaller<ProvisioningHook, JsonUnmarshallerContext> {
    private static ProvisioningHookJsonUnmarshaller instance;

    ProvisioningHookJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ProvisioningHook unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ProvisioningHook provisioningHook = new ProvisioningHook();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("payloadVersion")) {
                provisioningHook.setPayloadVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("targetArn")) {
                provisioningHook.setTargetArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return provisioningHook;
    }

    public static ProvisioningHookJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisioningHookJsonUnmarshaller();
        }
        return instance;
    }
}
