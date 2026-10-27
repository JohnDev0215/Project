package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RateIncreaseCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RateIncreaseCriteriaJsonUnmarshaller implements Unmarshaller<RateIncreaseCriteria, JsonUnmarshallerContext> {
    private static RateIncreaseCriteriaJsonUnmarshaller instance;

    RateIncreaseCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RateIncreaseCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RateIncreaseCriteria rateIncreaseCriteria = new RateIncreaseCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("numberOfNotifiedThings")) {
                rateIncreaseCriteria.setNumberOfNotifiedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("numberOfSucceededThings")) {
                rateIncreaseCriteria.setNumberOfSucceededThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return rateIncreaseCriteria;
    }

    public static RateIncreaseCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RateIncreaseCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
