package com.android.bluetooth.mapclient;

import com.google.common.base.Ascii;
import java.io.IOException;
import javax.obex.ClientSession;

/* JADX INFO: loaded from: classes.dex */
final class RequestSetNotificationRegistration extends Request {
    private static final String TYPE = "x-bt/MAP-NotificationRegistration";
    private final boolean mStatus;

    RequestSetNotificationRegistration(boolean z) {
        this.mStatus = z;
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters obexAppParameters = new ObexAppParameters();
        obexAppParameters.add(Ascii.SO, z ? (byte) 1 : (byte) 0);
        obexAppParameters.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }

    public boolean getStatus() {
        return this.mStatus;
    }
}
