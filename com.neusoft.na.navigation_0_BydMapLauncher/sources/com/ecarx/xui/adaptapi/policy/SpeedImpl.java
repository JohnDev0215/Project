package com.ecarx.xui.adaptapi.policy;

import android.util.Log;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SpeedImpl implements ISpeed {
    public static final String TAG = "SpeedImpl";
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.policy.SpeedImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 6) {
                Log.d(SpeedImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                if (bArr[5] == 1) {
                    SpeedImpl.this.mSpeedCallback.onSpeedDownReminder(1);
                }
            }
        }
    };
    private ISpeedCallback mSpeedCallback;

    @Override // com.ecarx.xui.adaptapi.policy.ISpeed
    public void registerSpeedCallback(ISpeedCallback iSpeedCallback) {
        if (iSpeedCallback == null) {
            Log.d(TAG, "registerSpeedCallback ISpeedCallback = null");
            return;
        }
        this.mSpeedCallback = iSpeedCallback;
        RpcManager.getInstance().registerListener(120258560, (byte) 6, this._onCallback);
        Log.d(TAG, "registerSpeedCallback");
    }

    @Override // com.ecarx.xui.adaptapi.policy.ISpeed
    public void unregisterSpeedCallback(ISpeedCallback iSpeedCallback) {
        Log.d(TAG, "unregisterSpeedCallback");
        RpcManager.getInstance().unregisterListener(120258560, (byte) 6, this._onCallback);
    }
}
