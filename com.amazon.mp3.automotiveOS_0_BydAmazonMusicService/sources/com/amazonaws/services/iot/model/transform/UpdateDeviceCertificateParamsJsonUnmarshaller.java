package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateDeviceCertificateParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class UpdateDeviceCertificateParamsJsonUnmarshaller implements Unmarshaller<UpdateDeviceCertificateParams, JsonUnmarshallerContext> {
    private static UpdateDeviceCertificateParamsJsonUnmarshaller instance;

    UpdateDeviceCertificateParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateDeviceCertificateParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        UpdateDeviceCertificateParams updateDeviceCertificateParams = new UpdateDeviceCertificateParams();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("action")) {
                updateDeviceCertificateParams.setAction(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateDeviceCertificateParams;
    }

    public static UpdateDeviceCertificateParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateDeviceCertificateParamsJsonUnmarshaller();
        }
        return instance;
    }
}
