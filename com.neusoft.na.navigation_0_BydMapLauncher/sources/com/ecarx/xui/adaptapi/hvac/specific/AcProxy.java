package com.ecarx.xui.adaptapi.hvac.specific;

import android.util.Log;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AcProxy {
    private static final String TAG = "AcProxy";
    private static AcProxy _aCProxy = new AcProxy();

    public static AcProxy getInstance() {
        if (_aCProxy == null) {
            _aCProxy = new AcProxy();
        }
        return _aCProxy;
    }

    private AcProxy() {
    }

    void registerListener(int i, byte b, RpcManager.OnRpcListener onRpcListener) {
        RpcManager.getInstance().registerListener(i, b, onRpcListener);
    }

    void unRegisterListener(int i, byte b, RpcManager.OnRpcListener onRpcListener) {
        RpcManager.getInstance().unregisterListener(i, b, onRpcListener);
    }

    byte[] getInitialize(byte b) {
        byte[] bArr = new byte[25];
        bArr[0] = b;
        Log.d(TAG, "set initialize data : " + Arrays.toString(bArr));
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message != null) {
            Log.d(TAG, "return initialize data : " + Arrays.toString(message));
            return message;
        }
        byte[] bArr2 = new byte[25];
        Log.d(TAG, "return initialize is null  subcode=" + ((int) b));
        return bArr2;
    }

    public void sendCommand(int i, byte[] bArr) {
        Log.d(TAG, "Set Info :  " + Arrays.toString(bArr) + ",opecode=" + i);
        RpcManager.getInstance().sendMessage(i, bArr);
    }
}
