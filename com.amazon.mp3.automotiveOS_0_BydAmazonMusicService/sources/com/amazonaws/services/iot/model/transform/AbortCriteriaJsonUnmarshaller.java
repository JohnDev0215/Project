package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AbortCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AbortCriteriaJsonUnmarshaller implements Unmarshaller<AbortCriteria, JsonUnmarshallerContext> {
    private static AbortCriteriaJsonUnmarshaller instance;

    AbortCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AbortCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AbortCriteria abortCriteria = new AbortCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("failureType")) {
                abortCriteria.setFailureType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("action")) {
                abortCriteria.setAction(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thresholdPercentage")) {
                abortCriteria.setThresholdPercentage(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("minNumberOfExecutedThings")) {
                abortCriteria.setMinNumberOfExecutedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return abortCriteria;
    }

    public static AbortCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AbortCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
