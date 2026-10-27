package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StreamSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class StreamSummaryJsonUnmarshaller implements Unmarshaller<StreamSummary, JsonUnmarshallerContext> {
    private static StreamSummaryJsonUnmarshaller instance;

    StreamSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StreamSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        StreamSummary streamSummary = new StreamSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("streamId")) {
                streamSummary.setStreamId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("streamArn")) {
                streamSummary.setStreamArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("streamVersion")) {
                streamSummary.setStreamVersion(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                streamSummary.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return streamSummary;
    }

    public static StreamSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StreamSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
