package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ViolationEventAdditionalInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventAdditionalInfoJsonUnmarshaller implements Unmarshaller<ViolationEventAdditionalInfo, JsonUnmarshallerContext> {
    private static ViolationEventAdditionalInfoJsonUnmarshaller instance;

    ViolationEventAdditionalInfoJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ViolationEventAdditionalInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ViolationEventAdditionalInfo violationEventAdditionalInfo = new ViolationEventAdditionalInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("confidenceLevel")) {
                violationEventAdditionalInfo.setConfidenceLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return violationEventAdditionalInfo;
    }

    public static ViolationEventAdditionalInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventAdditionalInfoJsonUnmarshaller();
        }
        return instance;
    }
}
