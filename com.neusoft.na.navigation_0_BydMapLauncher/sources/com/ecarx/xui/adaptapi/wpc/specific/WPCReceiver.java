package com.ecarx.xui.adaptapi.wpc.specific;

import android.util.Log;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class WPCReceiver {
    private static final String TAG = "WPCReceiver";
    private static final short cAccState = 25;
    private IWPCStateChangeListener mWpcStateChangeListener;
    private RpcManager rpc = RpcManager.getInstance();
    private RpcManager.OnRpcListener mOnCallbackWPC = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.wpc.specific.WPCReceiver.1
        public void onCallback(int i, byte[] bArr) {
            LogTool.d(WPCReceiver.TAG, "mOnCallbackWPC = " + Arrays.toString(bArr));
            byte b = bArr[1];
            if (b == 0) {
                WPCReceiver.this.mWpcStateChangeListener.onWorkingMode(bArr[2]);
                WPCReceiver.this.mWpcStateChangeListener.onChargingStatus(bArr[2]);
            } else if (b == 1) {
                WPCReceiver.this.mWpcStateChangeListener.onChargingStatus(101);
            }
        }
    };
    private RpcManager.OnRpcListener mOnCallBackAcc = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.wpc.specific.WPCReceiver.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d("gaoTest", "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 8) {
                LogTool.d(WPCReceiver.TAG, "bytes[6]=" + ((int) bArr[6]));
                byte b = bArr[6];
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        WPCReceiver.this.mWpcStateChangeListener.onWorkingMode(WPCReceiver.this.getWorkingMode());
                        WPCReceiver.this.mWpcStateChangeListener.onChargingStatus(WPCReceiver.this.getChargingStatus());
                        return;
                    } else if (b != 4) {
                        return;
                    }
                }
                WPCReceiver.this.mWpcStateChangeListener.onWorkingMode(-1);
                WPCReceiver.this.mWpcStateChangeListener.onChargingStatus(1);
            }
        }
    };

    public interface IWPCStateChangeListener {
        void onChargingStatus(int i);

        void onWorkingMode(int i);
    }

    private int getWPCState(int i) {
        if (i == 0) {
            return 4;
        }
        if (i == 101) {
            return 6;
        }
        if (i == 2) {
            return 2;
        }
        if (i != 3) {
            return (i == 4 || i == 5 || i == 6) ? 5 : 1;
        }
        return 4;
    }

    public WPCReceiver(IWPCStateChangeListener iWPCStateChangeListener) {
        this.mWpcStateChangeListener = iWPCStateChangeListener;
    }

    public void registerAccStateCallBack() {
        Log.d(TAG, "--------------registerAccStateCallBack");
        this.rpc.registerListener(1820, (byte) 8, this.mOnCallBackAcc);
    }

    public void registerDayNightModeCallBack() {
        this.rpc.registerListener(1640, (byte) 1, this.mOnCallbackWPC);
    }

    public void unregisterDayNigntModeCallBack() {
        this.rpc.registerListener(1640, (byte) 1, this.mOnCallbackWPC);
    }

    public int getWorkingMode() {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = this.rpc.getMessage(1649, bArr);
        if (message[1] == 0) {
            LogTool.d(TAG, "getWorkingMode data[1] = " + ((int) message[2]));
            byte b = message[2];
            if (b < 8) {
                return (7 == b || -1 == b || 6 == b) ? 1 : 2;
            }
        }
        LogTool.d(TAG, "getWorkingMode = " + Arrays.toString(message));
        return 1;
    }

    public int getChargingStatus() {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = this.rpc.getMessage(1649, bArr);
        byte b = message[1];
        if (b == 0) {
            LogTool.d(TAG, "getChargingStatus data[1] = " + ((int) message[1]));
            return getWPCState(message[2]);
        }
        if (b == 1) {
            return 6;
        }
        LogTool.d(TAG, "getChargingStatus = " + Arrays.toString(message));
        return 1;
    }
}
