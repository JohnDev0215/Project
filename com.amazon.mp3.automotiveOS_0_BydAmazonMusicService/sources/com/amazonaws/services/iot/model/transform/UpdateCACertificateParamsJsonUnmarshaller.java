package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateCACertificateParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class UpdateCACertificateParamsJsonUnmarshaller implements Unmarshaller<UpdateCACertificateParams, JsonUnmarshallerContext> {
    private static UpdateCACertificateParamsJsonUnmarshaller instance;

    UpdateCACertificateParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateCACertificateParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        UpdateCACertificateParams updateCACertificateParams = new UpdateCACertificateParams();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("action")) {
                updateCACertificateParams.setAction(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateCACertificateParams;
    }

    public static UpdateCACertificateParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateCACertificateParamsJsonUnmarshaller();
        }
        return instance;
    }
}
