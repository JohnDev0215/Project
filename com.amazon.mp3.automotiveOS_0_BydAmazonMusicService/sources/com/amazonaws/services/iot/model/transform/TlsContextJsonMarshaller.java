package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TlsContext;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class TlsContextJsonMarshaller {
    private static TlsContextJsonMarshaller instance;

    TlsContextJsonMarshaller() {
    }

    public void marshall(TlsContext tlsContext, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (tlsContext.getServerName() != null) {
            String serverName = tlsContext.getServerName();
            awsJsonWriter.name("serverName");
            awsJsonWriter.value(serverName);
        }
        awsJsonWriter.endObject();
    }

    public static TlsContextJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TlsContextJsonMarshaller();
        }
        return instance;
    }
}
