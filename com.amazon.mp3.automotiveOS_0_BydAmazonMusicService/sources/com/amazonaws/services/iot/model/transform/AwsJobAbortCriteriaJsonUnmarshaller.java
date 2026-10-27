package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobAbortCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobAbortCriteriaJsonUnmarshaller implements Unmarshaller<AwsJobAbortCriteria, JsonUnmarshallerContext> {
    private static AwsJobAbortCriteriaJsonUnmarshaller instance;

    AwsJobAbortCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobAbortCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobAbortCriteria awsJobAbortCriteria = new AwsJobAbortCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("failureType")) {
                awsJobAbortCriteria.setFailureType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("action")) {
                awsJobAbortCriteria.setAction(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thresholdPercentage")) {
                awsJobAbortCriteria.setThresholdPercentage(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("minNumberOfExecutedThings")) {
                awsJobAbortCriteria.setMinNumberOfExecutedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobAbortCriteria;
    }

    public static AwsJobAbortCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobAbortCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
