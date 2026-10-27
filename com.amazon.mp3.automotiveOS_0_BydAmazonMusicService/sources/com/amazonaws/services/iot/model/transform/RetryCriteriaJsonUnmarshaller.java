package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RetryCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RetryCriteriaJsonUnmarshaller implements Unmarshaller<RetryCriteria, JsonUnmarshallerContext> {
    private static RetryCriteriaJsonUnmarshaller instance;

    RetryCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RetryCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RetryCriteria retryCriteria = new RetryCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("failureType")) {
                retryCriteria.setFailureType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("numberOfRetries")) {
                retryCriteria.setNumberOfRetries(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return retryCriteria;
    }

    public static RetryCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RetryCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
