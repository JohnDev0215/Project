package com.qtxa.monitor.wifi;

import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;
import com.qtxa.service.IHandlerRequest;
import com.qtxa.service.e;

/* JADX INFO: loaded from: classes.dex */
public class HandlerWifiRequest implements IHandlerRequest {
    private static final String TAG = "wifi";

    @Override // com.qtxa.service.IHandlerRequest
    public void addCommCallback(com.qtxa.service.d dVar) {
        com.qtxa.hidps.b.b.a(TAG, "addCommCallback......");
    }

    @Override // com.qtxa.service.IHandlerRequest
    public String onCall(String str, e eVar) {
        com.qtxa.hidps.b.b.a(TAG, "onCall " + str);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = str;
        JniReportMessage.java_reportMessage(1, 1401, "wifi monitor", 2, defaultDetails);
        return null;
    }
}
