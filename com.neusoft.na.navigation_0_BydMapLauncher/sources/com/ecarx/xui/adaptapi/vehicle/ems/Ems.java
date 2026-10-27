package com.ecarx.xui.adaptapi.vehicle.ems;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.vehicle.ems.IEms;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Ems implements IEms {
    public static final int GET_EMS_STATE = 1;
    public static final int GET_SSM_STATE = 2;
    public static final byte GET_SUBCODE_EMS = 19;
    public static final byte GET_SUBCODE_VCU = 9;
    public static final int GET_VCU = 16;
    private static final int OPECODE_AC = 1822;
    private static final int OPECODE_VCU = 1821;
    public static final byte SUB_OPECODE_AC_THREE = 3;
    public static final byte SUB_OPECODE_VCU = 5;
    private static final String TAG = "Ems";
    private Context mContext;
    private int mEmsSsmStatus;
    private int mEmsState;
    private IEms.IEmsObserver mIEmsObserver;
    private int mVcuState;
    private int status = Integer.MIN_VALUE;
    private RpcManager.OnRpcListener _onCallbackEms = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.ems.Ems.1
        public void onCallback(int i, byte[] bArr) {
            if (bArr != null && i == 1822 && bArr[0] == 3) {
                Ems.this.mEmsState = bArr[1];
                Ems.this.mEmsSsmStatus = bArr[2];
                Log.d(Ems.TAG, "onCallback--->data[1]=" + ((int) bArr[1]) + "data[2]=" + ((int) bArr[2]) + ",mVcuState=" + Ems.this.mVcuState);
                if (1 == OfflineConfig.getE01OfflineConfig(26)) {
                    Ems ems = Ems.this;
                    if (ems.checkKCEngineRunningStatus(bArr[1], (byte) ems.mVcuState) != Ems.this.status) {
                        Ems ems2 = Ems.this;
                        ems2.status = ems2.checkKCEngineRunningStatus(bArr[1], (byte) ems2.mVcuState);
                        Log.d(Ems.TAG, "onEngineRunningStatusChanged--" + Ems.this.status);
                        Ems.this.mIEmsObserver.onEngineRunningStatusChanged(Ems.this.status);
                        return;
                    }
                    return;
                }
                if (1 == OfflineConfig.getE01OfflineConfig(29)) {
                    if (Ems.this.mEmsSsmStatus == 2 || Ems.this.mEmsSsmStatus == 3 || Ems.this.mEmsSsmStatus == 4 || Ems.this.mEmsSsmStatus == 5 || Ems.this.mEmsSsmStatus == 6) {
                        if (Ems.this.status != 4) {
                            Ems.this.status = 4;
                            Ems.this.mIEmsObserver.onEngineRunningStatusChanged(Ems.this.status);
                            return;
                        }
                        return;
                    }
                    if (Ems.this.checkEngineRunningStatus(bArr[1]) != Ems.this.status) {
                        Ems ems3 = Ems.this;
                        ems3.status = ems3.checkEngineRunningStatus(bArr[1]);
                        Log.d(Ems.TAG, "onEngineRunningStatusChanged--" + Ems.this.status);
                        Ems.this.mIEmsObserver.onEngineRunningStatusChanged(Ems.this.status);
                        return;
                    }
                    return;
                }
                if (Ems.this.checkEngineRunningStatus(bArr[1]) != Ems.this.status) {
                    Ems ems4 = Ems.this;
                    ems4.status = ems4.checkEngineRunningStatus(bArr[1]);
                    Log.d(Ems.TAG, "onEngineRunningStatusChanged--" + Ems.this.status);
                    Ems.this.mIEmsObserver.onEngineRunningStatusChanged(Ems.this.status);
                }
            }
        }
    };
    private RpcManager.OnRpcListener _onCallbackVcu = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.ems.Ems.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr != null && i == 1821 && bArr[0] == 5) {
                Ems.this.mVcuState = bArr[6];
                Log.d(Ems.TAG, "onCallback--->data[3]=" + ((int) bArr[6]) + ",mEmsState=" + Ems.this.mEmsState);
                if (1 == OfflineConfig.getE01OfflineConfig(26)) {
                    Ems ems = Ems.this;
                    if (ems.checkKCEngineRunningStatus((byte) ems.mEmsState, bArr[6]) != Ems.this.status) {
                        Ems ems2 = Ems.this;
                        ems2.status = ems2.checkKCEngineRunningStatus((byte) ems2.mEmsState, bArr[6]);
                        Log.d(Ems.TAG, "onEngineRunningStatusChanged--" + Ems.this.status);
                        Ems.this.mIEmsObserver.onEngineRunningStatusChanged(Ems.this.status);
                    }
                }
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public float getEngineOilPressure() {
        return 0.0f;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public int getEngineSpeed() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public float getTransmissionTemp() {
        return 0.0f;
    }

    public Ems(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public int getEngineRunningStatus() {
        byte[] bArr = new byte[25];
        bArr[0] = 19;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "ems data=" + Arrays.toString(message));
        if (message != null) {
            this.mEmsState = message[1];
            this.mEmsSsmStatus = message[2];
            Log.d(TAG, "getEngineRunningStatusmEmsSsmStatus=" + this.mEmsSsmStatus + "mEmsState=" + this.mEmsState);
            if (1 == OfflineConfig.getE01OfflineConfig(26)) {
                byte[] bArr2 = new byte[25];
                bArr2[0] = 9;
                byte[] message2 = RpcManager.getInstance().getMessage(1881, bArr2);
                this.mVcuState = message2[16];
                Log.d(TAG, "vcu[GET_VCU]" + ((int) message2[16]));
                this.status = checkKCEngineRunningStatus(message[1], message2[16]);
            } else if (1 == OfflineConfig.getE01OfflineConfig(29)) {
                int i = this.mEmsSsmStatus;
                if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
                    if (this.status != 4) {
                        this.status = 4;
                    }
                } else {
                    this.status = checkEngineRunningStatus(message[1]);
                }
            } else {
                this.status = checkEngineRunningStatus(message[1]);
            }
        }
        return this.status;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public boolean attachEmsObserver(IEms.IEmsObserver iEmsObserver) {
        Log.d(TAG, "attachEmsObserver");
        if (iEmsObserver == null) {
            return false;
        }
        this.mIEmsObserver = iEmsObserver;
        RpcManager.getInstance().registerListener(1822, (byte) 3, this._onCallbackEms);
        RpcManager.getInstance().registerListener(1821, (byte) 5, this._onCallbackVcu);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public void detachEmsObserver(IEms.IEmsObserver iEmsObserver) {
        if (this.mIEmsObserver != null) {
            this.mIEmsObserver = null;
        }
        RpcManager.getInstance().unregisterListener(1822, (byte) 3, this._onCallbackEms);
        RpcManager.getInstance().unregisterListener(1821, (byte) 5, this._onCallbackVcu);
    }

    public boolean isEngineRunningStatus(byte b) {
        Log.d(TAG, "isEngineRunningStatus data -->" + ((int) b));
        return b == 0 || b == 1 || b == 3 || b == 7;
    }

    public int checkKCEngineRunningStatus(byte b, byte b2) {
        Log.d(TAG, "checkKCEngineRunningStatus data -->" + ((int) b));
        if (b == 3 || b2 == 1) {
            return 3;
        }
        if (b == 0) {
            return 1;
        }
        if (b != 1) {
            return b != 3 ? Integer.MIN_VALUE : 3;
        }
        return 2;
    }

    public int checkEngineRunningStatus(byte b) {
        Log.d(TAG, "checkEngineRunningStatus data -->" + ((int) b));
        if (b == 0) {
            return 1;
        }
        if (b != 1) {
            return b != 3 ? Integer.MIN_VALUE : 3;
        }
        return 2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IEms
    public float getAntifreezeCoolantTemp() {
        byte[] bArr = new byte[25];
        bArr[0] = Define.cSubCodeSetADS;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d("ems", "data = null");
            return 0.0f;
        }
        Log.d("ems", "getAntifreezeCoolantTemp" + Arrays.toString(message));
        return message[1];
    }
}
