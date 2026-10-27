package com.android.bluetooth.pbapclient;

import android.os.Handler;
import javax.obex.PasswordAuthentication;

/* JADX INFO: loaded from: classes.dex */
class BluetoothPbapObexAuthenticator implements javax.obex.Authenticator {
    private static final boolean DBG = false;
    private static final String TAG = "BtPbapObexAuthenticator";
    private final Handler mCallback;
    private String mSessionKey = "0000";

    BluetoothPbapObexAuthenticator(Handler callback) {
        this.mCallback = callback;
    }

    public PasswordAuthentication onAuthenticationChallenge(String description, boolean isUserIdRequired, boolean isFullAccess) {
        String str = this.mSessionKey;
        if (str == null || str.length() == 0) {
            return null;
        }
        PasswordAuthentication pa = new PasswordAuthentication((byte[]) null, this.mSessionKey.getBytes());
        return pa;
    }

    public byte[] onAuthenticationResponse(byte[] userName) {
        return null;
    }
}
