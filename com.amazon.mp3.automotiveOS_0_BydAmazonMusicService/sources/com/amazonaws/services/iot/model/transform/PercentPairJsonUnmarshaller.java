package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PercentPair;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PercentPairJsonUnmarshaller implements Unmarshaller<PercentPair, JsonUnmarshallerContext> {
    private static PercentPairJsonUnmarshaller instance;

    PercentPairJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PercentPair unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PercentPair percentPair = new PercentPair();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("percent")) {
                percentPair.setPercent(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("value")) {
                percentPair.setValue(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return percentPair;
    }

    public static PercentPairJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PercentPairJsonUnmarshaller();
        }
        return instance;
    }
}
