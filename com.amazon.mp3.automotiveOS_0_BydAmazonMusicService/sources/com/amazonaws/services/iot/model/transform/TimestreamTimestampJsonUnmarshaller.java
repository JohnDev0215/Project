package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimestreamTimestamp;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamTimestampJsonUnmarshaller implements Unmarshaller<TimestreamTimestamp, JsonUnmarshallerContext> {
    private static TimestreamTimestampJsonUnmarshaller instance;

    TimestreamTimestampJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TimestreamTimestamp unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TimestreamTimestamp timestreamTimestamp = new TimestreamTimestamp();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("value")) {
                timestreamTimestamp.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("unit")) {
                timestreamTimestamp.setUnit(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return timestreamTimestamp;
    }

    public static TimestreamTimestampJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamTimestampJsonUnmarshaller();
        }
        return instance;
    }
}
