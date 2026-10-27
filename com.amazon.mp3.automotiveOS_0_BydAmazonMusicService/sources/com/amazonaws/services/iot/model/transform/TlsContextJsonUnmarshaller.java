package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TlsContext;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TlsContextJsonUnmarshaller implements Unmarshaller<TlsContext, JsonUnmarshallerContext> {
    private static TlsContextJsonUnmarshaller instance;

    TlsContextJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TlsContext unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TlsContext tlsContext = new TlsContext();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("serverName")) {
                tlsContext.setServerName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return tlsContext;
    }

    public static TlsContextJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TlsContextJsonUnmarshaller();
        }
        return instance;
    }
}
