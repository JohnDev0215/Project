package com.ecarx.xui.adaptapi.hvac.specific;

import android.util.Log;
import com.ecarx.xui.adaptapi.hvac.inf.IDataListener;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class HvacReceiver {
    private static final int OPECODE_AC = 1822;
    public static final int OPECODE_AC_SEAT = 1820;
    private static final int OPECODE_PEPS = 1820;
    private static final int OPECODE_SEATWIND = 1821;
    private static final int OPECODE_VCU = 1821;
    private static final String TAG = "HvacReceiver";
    private IDataListener _acBean;
    private RpcManager.OnRpcListener _onCallbackPeps = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.1
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 8) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackVcu = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1821 && bArr[0] == 5) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackSeat = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.3
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 9) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackPassWind = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.4
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 6) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackAcOne = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.5
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 1) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackAcTwo = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.6
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 2) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackAcThree = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.7
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 3) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackAcFive = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.8
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 5) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackACSeat = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.9
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 12) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackACLostConnection = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver.10
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            Log.d(HvacReceiver.TAG, "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1835 && bArr[0] == 1) {
                HvacReceiver.this._acBean.insertData(i, bArr);
            }
        }
    };

    public HvacReceiver(IDataListener iDataListener) {
        registerLintener();
        this._acBean = iDataListener;
    }

    public void registerLintener() {
        AcProxy.getInstance().registerListener(1820, (byte) 8, this._onCallbackPeps);
        AcProxy.getInstance().registerListener(1820, (byte) 9, this._onCallbackSeat);
        AcProxy.getInstance().registerListener(1821, (byte) 5, this._onCallbackVcu);
        AcProxy.getInstance().registerListener(1820, (byte) 6, this._onCallbackPassWind);
        AcProxy.getInstance().registerListener(1822, (byte) 1, this._onCallbackAcOne);
        AcProxy.getInstance().registerListener(1822, (byte) 2, this._onCallbackAcTwo);
        AcProxy.getInstance().registerListener(1822, (byte) 3, this._onCallbackAcThree);
        AcProxy.getInstance().registerListener(1822, (byte) 5, this._onCallbackAcFive);
        AcProxy.getInstance().registerListener(1820, (byte) 12, this._onCallbackACSeat);
        AcProxy.getInstance().registerListener(1835, (byte) 1, this._onCallbackACLostConnection);
    }

    public void unRegisterListener() {
        AcProxy.getInstance().unRegisterListener(1820, (byte) 8, this._onCallbackPeps);
        AcProxy.getInstance().unRegisterListener(1820, (byte) 9, this._onCallbackSeat);
        AcProxy.getInstance().unRegisterListener(1821, (byte) 5, this._onCallbackVcu);
        AcProxy.getInstance().unRegisterListener(1820, (byte) 6, this._onCallbackPassWind);
        AcProxy.getInstance().unRegisterListener(1822, (byte) 1, this._onCallbackAcOne);
        AcProxy.getInstance().unRegisterListener(1822, (byte) 2, this._onCallbackAcTwo);
        AcProxy.getInstance().unRegisterListener(1822, (byte) 3, this._onCallbackAcThree);
        AcProxy.getInstance().unRegisterListener(1822, (byte) 5, this._onCallbackAcFive);
        AcProxy.getInstance().unRegisterListener(1820, (byte) 12, this._onCallbackACSeat);
        AcProxy.getInstance().unRegisterListener(1835, (byte) 1, this._onCallbackACLostConnection);
    }

    public void initializeData() {
        initialACData((byte) 3);
        initialACData((byte) 4);
        initialACData((byte) 8);
        initialACData((byte) 20);
        initialACData((byte) 17);
        initialACData((byte) 22);
        initialACData((byte) 18);
    }

    private void initialACData(byte b) {
        byte[] initialize = AcProxy.getInstance().getInitialize(b);
        Log.d(TAG, "init data=" + Arrays.toString(initialize));
        this._acBean.initialData(initialize);
    }

    public void initAqsState() {
        byte[] initialize = AcProxy.getInstance().getInitialize((byte) 17);
        byte[] initialize2 = AcProxy.getInstance().getInitialize((byte) 18);
        Log.d(TAG, "initAqsState=" + Arrays.toString(initialize) + ", init CO2 = " + Arrays.toString(initialize2));
        this._acBean.initAqsState(initialize, initialize2);
    }
}
