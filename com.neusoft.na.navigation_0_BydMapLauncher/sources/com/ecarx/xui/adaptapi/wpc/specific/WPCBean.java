package com.ecarx.xui.adaptapi.wpc.specific;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.wpc.WPC;
import com.ecarx.xui.adaptapi.wpc.specific.WPCReceiver;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class WPCBean {
    private static final String cTag = "WPCBean";
    private WPC.StateListener mStateListener;
    private RpcManager rpc = RpcManager.getInstance();
    private WPCReceiver mWPCReceiver = new WPCReceiver(new WPCReceiver.IWPCStateChangeListener() { // from class: com.ecarx.xui.adaptapi.wpc.specific.WPCBean.1
        @Override // com.ecarx.xui.adaptapi.wpc.specific.WPCReceiver.IWPCStateChangeListener
        public void onWorkingMode(int i) {
            WPCBean.this.mStateListener.onWorkingMode((7 == i || -1 == i || 6 == i) ? 1 : 2);
            LogTool.d(WPCBean.cTag, "onWorkingMode = " + i);
        }

        @Override // com.ecarx.xui.adaptapi.wpc.specific.WPCReceiver.IWPCStateChangeListener
        public void onChargingStatus(int i) {
            WPCBean.this.mStateListener.onChargingStatus(WPCBean.this.getWPCState(i));
            LogTool.d(WPCBean.cTag, "onChargingStatus = " + i);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public int getWPCState(int i) {
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

    public WPCBean(Context context) {
    }

    public int setWorkingMode(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) (i == 2 ? 1 : 0);
        this.rpc.sendMessage(1639, bArr);
        LogTool.d(cTag, "setWorkingMode = " + i);
        return i;
    }

    public boolean getAccState() {
        byte[] bArr = new byte[25];
        bArr[0] = 6;
        byte[] message = this.rpc.getMessage(1881, bArr);
        if (message == null || message.length == 0) {
            return false;
        }
        LogTool.d(cTag, "bytes[13]=" + ((int) message[11]));
        byte b = message[11];
        return b == 0 || !(b == 1 || b == 2 || b != 4);
    }

    public int getWorkingMode() {
        if (getAccState()) {
            Log.d(cTag, "getAccState accState=" + getAccState());
            return 1;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = this.rpc.getMessage(1649, bArr);
        if (message[1] == 0) {
            LogTool.d(cTag, "getWorkingMode = " + ((int) message[2]));
            byte b = message[2];
            if (b < 8) {
                return (7 == b || -1 == b || 6 == b) ? 1 : 2;
            }
        }
        LogTool.d(cTag, "getWorkingMode = " + Arrays.toString(message));
        return 1;
    }

    public int getChargingStatus() {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = this.rpc.getMessage(1649, bArr);
        byte b = message[1];
        if (b == 0) {
            LogTool.d(cTag, "getChargingStatus = " + ((int) message[2]));
            return getWPCState(message[2]);
        }
        if (b == 1) {
            return 6;
        }
        LogTool.d(cTag, "getChargingStatus = " + Arrays.toString(message));
        return 1;
    }

    public void setStateListener(WPC.StateListener stateListener) {
        this.mStateListener = stateListener;
        this.mWPCReceiver.registerDayNightModeCallBack();
        this.mWPCReceiver.registerAccStateCallBack();
    }
}
