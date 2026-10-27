package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ExponentialRolloutRate;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ExponentialRolloutRateJsonUnmarshaller implements Unmarshaller<ExponentialRolloutRate, JsonUnmarshallerContext> {
    private static ExponentialRolloutRateJsonUnmarshaller instance;

    ExponentialRolloutRateJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ExponentialRolloutRate unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ExponentialRolloutRate exponentialRolloutRate = new ExponentialRolloutRate();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("baseRatePerMinute")) {
                exponentialRolloutRate.setBaseRatePerMinute(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("incrementFactor")) {
                exponentialRolloutRate.setIncrementFactor(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rateIncreaseCriteria")) {
                exponentialRolloutRate.setRateIncreaseCriteria(RateIncreaseCriteriaJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return exponentialRolloutRate;
    }

    public static ExponentialRolloutRateJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ExponentialRolloutRateJsonUnmarshaller();
        }
        return instance;
    }
}
