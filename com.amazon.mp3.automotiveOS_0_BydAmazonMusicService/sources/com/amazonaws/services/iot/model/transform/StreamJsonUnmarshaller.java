package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Stream;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class StreamJsonUnmarshaller implements Unmarshaller<Stream, JsonUnmarshallerContext> {
    private static StreamJsonUnmarshaller instance;

    StreamJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Stream unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Stream stream = new Stream();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("streamId")) {
                stream.setStreamId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("fileId")) {
                stream.setFileId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return stream;
    }

    public static StreamJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StreamJsonUnmarshaller();
        }
        return instance;
    }
}
