package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.RetainedMessageSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RetainedMessageSummaryJsonUnmarshaller implements Unmarshaller<RetainedMessageSummary, JsonUnmarshallerContext> {
    private static RetainedMessageSummaryJsonUnmarshaller instance;

    RetainedMessageSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RetainedMessageSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RetainedMessageSummary retainedMessageSummary = new RetainedMessageSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("topic")) {
                retainedMessageSummary.setTopic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("payloadSize")) {
                retainedMessageSummary.setPayloadSize(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("qos")) {
                retainedMessageSummary.setQos(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedTime")) {
                retainedMessageSummary.setLastModifiedTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return retainedMessageSummary;
    }

    public static RetainedMessageSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RetainedMessageSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
