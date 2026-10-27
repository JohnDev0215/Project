package com.android.bluetooth.mapclient;

import java.io.IOException;
import javax.obex.ClientSession;

/* JADX INFO: loaded from: classes.dex */
final class RequestUpdateInbox extends Request {
    private static final String TYPE = "x-bt/MAP-messageUpdate";

    RequestUpdateInbox() {
        this.mHeaderSet.setHeader(66, TYPE);
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }
}
