package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExponentialRolloutRate;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobExponentialRolloutRateJsonUnmarshaller implements Unmarshaller<AwsJobExponentialRolloutRate, JsonUnmarshallerContext> {
    private static AwsJobExponentialRolloutRateJsonUnmarshaller instance;

    AwsJobExponentialRolloutRateJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobExponentialRolloutRate unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobExponentialRolloutRate awsJobExponentialRolloutRate = new AwsJobExponentialRolloutRate();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("baseRatePerMinute")) {
                awsJobExponentialRolloutRate.setBaseRatePerMinute(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("incrementFactor")) {
                awsJobExponentialRolloutRate.setIncrementFactor(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rateIncreaseCriteria")) {
                awsJobExponentialRolloutRate.setRateIncreaseCriteria(AwsJobRateIncreaseCriteriaJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobExponentialRolloutRate;
    }

    public static AwsJobExponentialRolloutRateJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobExponentialRolloutRateJsonUnmarshaller();
        }
        return instance;
    }
}
