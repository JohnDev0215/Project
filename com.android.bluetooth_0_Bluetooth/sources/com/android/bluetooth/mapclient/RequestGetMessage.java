package com.android.bluetooth.mapclient;

import android.util.Log;
import com.android.bluetooth.mapclient.MasClient;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.obex.ClientSession;

/* JADX INFO: loaded from: classes.dex */
final class RequestGetMessage extends Request {
    private static final String TAG = "RequestGetMessage";
    private static final String TYPE = "x-bt/message";
    private Bmessage mBmessage;

    RequestGetMessage(String str, MasClient.CharsetType charsetType, boolean z) {
        this.mHeaderSet.setHeader(1, str);
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters obexAppParameters = new ObexAppParameters();
        obexAppParameters.add(Ascii.DC4, MasClient.CharsetType.UTF_8.equals(charsetType) ? (byte) 1 : (byte) 0);
        obexAppParameters.add((byte) 10, z ? (byte) 1 : (byte) 0);
        obexAppParameters.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.mapclient.Request
    protected void readResponse(InputStream stream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while (true) {
            try {
                int len = stream.read(buf);
                if (len != -1) {
                    baos.write(buf, 0, len);
                }
            } catch (IOException e) {
                Log.e(TAG, "I/O exception while reading response", e);
            }
            try {
                break;
            } catch (UnsupportedEncodingException e2) {
                Log.e(TAG, "Coudn't decode the bmessage with UTF-8. Something must be really messed up.");
                return;
            }
        }
        String bmsg = baos.toString(StandardCharsets.UTF_8.name());
        Bmessage bmessageCreateBmessage = BmessageParser.createBmessage(bmsg);
        this.mBmessage = bmessageCreateBmessage;
        if (bmessageCreateBmessage == null) {
            this.mResponseCode = 208;
        }
    }

    public Bmessage getMessage() {
        return this.mBmessage;
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
        executeGet(session);
    }
}
