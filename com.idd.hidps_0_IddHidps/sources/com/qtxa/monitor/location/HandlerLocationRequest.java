package com.qtxa.monitor.location;

import com.qtxa.hidps.b.b;
import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;
import com.qtxa.service.IHandlerRequest;
import com.qtxa.service.d;
import com.qtxa.service.e;

/* JADX INFO: loaded from: classes.dex */
public class HandlerLocationRequest implements IHandlerRequest {
    private static final String TAG = "location";

    @Override // com.qtxa.service.IHandlerRequest
    public void addCommCallback(d dVar) {
        b.a(TAG, "addCommCallback......");
    }

    @Override // com.qtxa.service.IHandlerRequest
    public String onCall(String str, e eVar) {
        b.a(TAG, "onCall " + str);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = str;
        JniReportMessage.java_reportMessage(1, 1402, "location monitor", 2, defaultDetails);
        return null;
    }
}
