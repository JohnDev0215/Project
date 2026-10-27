package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class OTAUpdateSummaryJsonUnmarshaller implements Unmarshaller<OTAUpdateSummary, JsonUnmarshallerContext> {
    private static OTAUpdateSummaryJsonUnmarshaller instance;

    OTAUpdateSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OTAUpdateSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OTAUpdateSummary oTAUpdateSummary = new OTAUpdateSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("otaUpdateId")) {
                oTAUpdateSummary.setOtaUpdateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("otaUpdateArn")) {
                oTAUpdateSummary.setOtaUpdateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                oTAUpdateSummary.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return oTAUpdateSummary;
    }

    public static OTAUpdateSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
