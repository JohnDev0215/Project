package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobRateIncreaseCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobRateIncreaseCriteriaJsonUnmarshaller implements Unmarshaller<AwsJobRateIncreaseCriteria, JsonUnmarshallerContext> {
    private static AwsJobRateIncreaseCriteriaJsonUnmarshaller instance;

    AwsJobRateIncreaseCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobRateIncreaseCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobRateIncreaseCriteria awsJobRateIncreaseCriteria = new AwsJobRateIncreaseCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("numberOfNotifiedThings")) {
                awsJobRateIncreaseCriteria.setNumberOfNotifiedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("numberOfSucceededThings")) {
                awsJobRateIncreaseCriteria.setNumberOfSucceededThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobRateIncreaseCriteria;
    }

    public static AwsJobRateIncreaseCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobRateIncreaseCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
