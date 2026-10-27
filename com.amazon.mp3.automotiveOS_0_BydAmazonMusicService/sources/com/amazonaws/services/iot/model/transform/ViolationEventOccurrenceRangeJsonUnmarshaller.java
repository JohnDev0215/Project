package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ViolationEventOccurrenceRange;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventOccurrenceRangeJsonUnmarshaller implements Unmarshaller<ViolationEventOccurrenceRange, JsonUnmarshallerContext> {
    private static ViolationEventOccurrenceRangeJsonUnmarshaller instance;

    ViolationEventOccurrenceRangeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ViolationEventOccurrenceRange unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ViolationEventOccurrenceRange violationEventOccurrenceRange = new ViolationEventOccurrenceRange();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("startTime")) {
                violationEventOccurrenceRange.setStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("endTime")) {
                violationEventOccurrenceRange.setEndTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return violationEventOccurrenceRange;
    }

    public static ViolationEventOccurrenceRangeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventOccurrenceRangeJsonUnmarshaller();
        }
        return instance;
    }
}
