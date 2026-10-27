package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ServerCertificateSummary;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ServerCertificateSummaryJsonMarshaller {
    private static ServerCertificateSummaryJsonMarshaller instance;

    ServerCertificateSummaryJsonMarshaller() {
    }

    public void marshall(ServerCertificateSummary serverCertificateSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (serverCertificateSummary.getServerCertificateArn() != null) {
            String serverCertificateArn = serverCertificateSummary.getServerCertificateArn();
            awsJsonWriter.name("serverCertificateArn");
            awsJsonWriter.value(serverCertificateArn);
        }
        if (serverCertificateSummary.getServerCertificateStatus() != null) {
            String serverCertificateStatus = serverCertificateSummary.getServerCertificateStatus();
            awsJsonWriter.name("serverCertificateStatus");
            awsJsonWriter.value(serverCertificateStatus);
        }
        if (serverCertificateSummary.getServerCertificateStatusDetail() != null) {
            String serverCertificateStatusDetail = serverCertificateSummary.getServerCertificateStatusDetail();
            awsJsonWriter.name("serverCertificateStatusDetail");
            awsJsonWriter.value(serverCertificateStatusDetail);
        }
        awsJsonWriter.endObject();
    }

    public static ServerCertificateSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ServerCertificateSummaryJsonMarshaller();
        }
        return instance;
    }
}
