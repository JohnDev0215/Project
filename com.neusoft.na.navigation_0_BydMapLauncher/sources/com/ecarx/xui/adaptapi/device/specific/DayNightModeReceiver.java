package com.ecarx.xui.adaptapi.device.specific;

import android.util.Log;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class DayNightModeReceiver {
    private static final String TAG = "DayNightModeReceiver";
    private IModeListener mIModeListener;
    private RpcManager.OnRpcListener mOnCallbackDayNightMode = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.device.specific.DayNightModeReceiver.1
        public void onCallback(int i, byte[] bArr) {
            if (bArr != null) {
                Log.d(DayNightModeReceiver.TAG, "mOnCallbackDayNightMode.onCallback() bytes: " + Arrays.toString(bArr));
                DayNightModeReceiver.this.mIModeListener.onDayNightModeChange(bArr[5]);
            } else {
                Log.d(DayNightModeReceiver.TAG, "mOnCallbackDayNightMode.onCallback() bytes is null.");
            }
        }
    };

    public interface IModeListener {
        void onDayNightModeChange(int i);
    }

    public DayNightModeReceiver(IModeListener iModeListener) {
        this.mIModeListener = iModeListener;
    }

    public void registerDayNightModeCallBack() {
        RpcManager.getInstance().registerListener(1820, (byte) 12, this.mOnCallbackDayNightMode);
    }

    public void unregisterDayNigntModeCallBack() {
        RpcManager.getInstance().unregisterListener(1820, (byte) 12, this.mOnCallbackDayNightMode);
    }
}
