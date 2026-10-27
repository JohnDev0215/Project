package com.ecarx.xui.adaptapi.vehicle.tcu;

import android.content.Context;
import android.util.Log;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TcuImpl implements ITcu {
    private static final int GETRPCOPECODE = 1881;
    public static final String TAG = "TcuImpl";
    private Context mContext;
    private int mGear;
    private ITcuCallback mTcuCallbackImpl;
    private ITcuState mTcuStateImpl = new TcuStatusImpl();
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.tcu.TcuImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1834 && bArr[0] == 2) {
                Log.d(TcuImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[1];
                Log.d(TcuImpl.TAG, "mGear = " + TcuImpl.this.mGear);
                Log.d(TcuImpl.TAG, "gear = " + ((int) b));
                if (b != TcuImpl.this.mGear) {
                    TcuImpl.this.mGear = b;
                    TcuImpl.this.mTcuCallbackImpl.onTcuChanged(TcuImpl.this.mTcuStateImpl);
                }
            }
        }
    };

    public TcuImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcu
    public ITcuState getTcuState() {
        if (this.mTcuStateImpl == null) {
            this.mTcuStateImpl = new TcuStatusImpl();
        }
        Log.d(TAG, "getLightingState");
        return this.mTcuStateImpl;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcu
    public void setTcuCallback(ITcuCallback iTcuCallback) {
        Log.d(TAG, "setTcuCallback");
        this.mTcuCallbackImpl = iTcuCallback;
        byte[] bArr = new byte[25];
        bArr[0] = 23;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "get mGear result = " + Arrays.toString(message));
        this.mGear = message[8];
        RpcManager.getInstance().registerListener(1834, (byte) 2, this._onCallback);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcu
    public void unsetTcuCallback(ITcuCallback iTcuCallback) {
        Log.d(TAG, "unsetTcuCallback");
        RpcManager.getInstance().unregisterListener(1834, (byte) 2, this._onCallback);
        this.mTcuCallbackImpl = null;
    }
}
