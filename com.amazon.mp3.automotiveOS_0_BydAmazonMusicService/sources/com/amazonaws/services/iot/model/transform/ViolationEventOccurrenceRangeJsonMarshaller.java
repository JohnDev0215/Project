package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ViolationEventOccurrenceRange;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class ViolationEventOccurrenceRangeJsonMarshaller {
    private static ViolationEventOccurrenceRangeJsonMarshaller instance;

    ViolationEventOccurrenceRangeJsonMarshaller() {
    }

    public void marshall(ViolationEventOccurrenceRange violationEventOccurrenceRange, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (violationEventOccurrenceRange.getStartTime() != null) {
            Date startTime = violationEventOccurrenceRange.getStartTime();
            awsJsonWriter.name("startTime");
            awsJsonWriter.value(startTime);
        }
        if (violationEventOccurrenceRange.getEndTime() != null) {
            Date endTime = violationEventOccurrenceRange.getEndTime();
            awsJsonWriter.name("endTime");
            awsJsonWriter.value(endTime);
        }
        awsJsonWriter.endObject();
    }

    public static ViolationEventOccurrenceRangeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ViolationEventOccurrenceRangeJsonMarshaller();
        }
        return instance;
    }
}
