package com.android.bluetooth.mapclient;

import android.net.INetd;
import com.android.bluetooth.mapclient.MasClient;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.math.BigInteger;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
final class RequestPushMessage extends Request {
    private static final String TYPE = "x-bt/message";
    private Bmessage mMsg;
    private String mMsgHandle;

    private RequestPushMessage(String folder) {
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(1, folder == null ? INetd.NEXTHOP_NONE : folder);
    }

    RequestPushMessage(String str, Bmessage bmessage, MasClient.CharsetType charsetType, boolean z, boolean z2) {
        this(str);
        this.mMsg = bmessage;
        ObexAppParameters obexAppParameters = new ObexAppParameters();
        obexAppParameters.add(Ascii.VT, z ? (byte) 1 : (byte) 0);
        obexAppParameters.add(Ascii.FF, z2 ? (byte) 1 : (byte) 0);
        obexAppParameters.add(Ascii.DC4, charsetType == MasClient.CharsetType.NATIVE ? (byte) 0 : (byte) 1);
        obexAppParameters.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.mapclient.Request
    protected void readResponseHeaders(HeaderSet headerset) {
        try {
            String handle = (String) headerset.getHeader(1);
            if (handle != null) {
                new BigInteger(handle, 16);
                this.mMsgHandle = handle;
            }
        } catch (IOException e) {
            this.mResponseCode = 208;
        } catch (NumberFormatException e2) {
            this.mResponseCode = 208;
        }
    }

    public Bmessage getBMsg() {
        return this.mMsg;
    }

    public String getMsgHandle() {
        return this.mMsgHandle;
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executePut(session, BmessageBuilder.createBmessage(this.mMsg).getBytes());
    }
}
