package com.android.bluetooth.mapclient;

import android.util.Log;
import com.google.common.base.Ascii;
import java.io.IOException;
import javax.obex.ClientSession;

/* JADX INFO: loaded from: classes.dex */
final class RequestSetMessageStatus extends Request {
    private static final String TAG = "RequestSetMessageStatus";
    private static final String TYPE = "x-bt/messageStatus";
    private static StatusIndicator mStatusInd;
    private static byte mValue;

    public enum StatusIndicator {
        READ,
        DELETED
    }

    public RequestSetMessageStatus(String handle, StatusIndicator statusInd, byte value) {
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(1, handle);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add(Ascii.ETB, statusInd == StatusIndicator.READ ? (byte) 0 : (byte) 1);
        oap.add(Ascii.CAN, value != 1 ? (byte) 0 : (byte) 1);
        oap.addToHeaderSet(this.mHeaderSet);
        mStatusInd = statusInd;
        mValue = value;
    }

    public StatusIndicator getStatusIndicator() {
        return mStatusInd;
    }

    public byte getValue() {
        return mValue;
    }

    public String getHandle() {
        try {
            return (String) this.mHeaderSet.getHeader(1);
        } catch (IOException e) {
            Log.e(TAG, "Unexpected exception while reading handle!", e);
            return null;
        }
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }
}
