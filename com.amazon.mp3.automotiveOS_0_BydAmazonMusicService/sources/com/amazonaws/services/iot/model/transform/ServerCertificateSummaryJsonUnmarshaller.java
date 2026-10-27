package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ServerCertificateSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ServerCertificateSummaryJsonUnmarshaller implements Unmarshaller<ServerCertificateSummary, JsonUnmarshallerContext> {
    private static ServerCertificateSummaryJsonUnmarshaller instance;

    ServerCertificateSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ServerCertificateSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ServerCertificateSummary serverCertificateSummary = new ServerCertificateSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("serverCertificateArn")) {
                serverCertificateSummary.setServerCertificateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serverCertificateStatus")) {
                serverCertificateSummary.setServerCertificateStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("serverCertificateStatusDetail")) {
                serverCertificateSummary.setServerCertificateStatusDetail(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return serverCertificateSummary;
    }

    public static ServerCertificateSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ServerCertificateSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
