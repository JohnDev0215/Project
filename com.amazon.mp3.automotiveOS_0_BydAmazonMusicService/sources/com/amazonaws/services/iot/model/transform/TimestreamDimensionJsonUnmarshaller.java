package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimestreamDimension;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamDimensionJsonUnmarshaller implements Unmarshaller<TimestreamDimension, JsonUnmarshallerContext> {
    private static TimestreamDimensionJsonUnmarshaller instance;

    TimestreamDimensionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TimestreamDimension unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TimestreamDimension timestreamDimension = new TimestreamDimension();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                timestreamDimension.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("value")) {
                timestreamDimension.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return timestreamDimension;
    }

    public static TimestreamDimensionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamDimensionJsonUnmarshaller();
        }
        return instance;
    }
}
