package com.ecarx.xui.adaptapi.vehicle.ota;

import android.os.RemoteException;
import android.util.Log;
import cerence.ark.assistant.controller.domain.cc.CCRequestsEnum;
import com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
class OtaSessionProxy implements IOtaSession {
    private IOtaSessionAidlInterface _aidl;
    private final String cTag = "OtaSessionProxy";

    public void setAidl(IOtaSessionAidlInterface iOtaSessionAidlInterface) {
        this._aidl = iOtaSessionAidlInterface;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public boolean cancel() {
        Log.d("OtaSessionProxy", CCRequestsEnum.CANCEL);
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return false;
        }
        try {
            return iOtaSessionAidlInterface.cancel();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "cancel error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public boolean couldBeginInstallRightNow() {
        Log.d("OtaSessionProxy", "couldBeginInstallRightNow");
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return false;
        }
        try {
            return iOtaSessionAidlInterface.couldBeginInstallRightNow();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "couldBeginInstallRightNow error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public int getOtaProgress() {
        Log.d("OtaSessionProxy", "getOtaProgress");
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return 0;
        }
        try {
            return iOtaSessionAidlInterface.getOtaProgress();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "getOtaProgress error:" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public boolean ifSystemWillRebootAfterOta() {
        Log.d("OtaSessionProxy", "ifSystemWillRebootAfterOta");
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return false;
        }
        try {
            return iOtaSessionAidlInterface.ifSystemWillRebootAfterOta();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "ifSystemWillRebootAfterOta error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public boolean isInstallationStarted() {
        Log.d("OtaSessionProxy", "isInstallationStarted");
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return false;
        }
        try {
            return iOtaSessionAidlInterface.isInstallationStarted();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "isInstallationStarted error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOtaSession
    public int getOtaConsumingTime() {
        Log.d("OtaSessionProxy", "getOtaConsumingTime");
        IOtaSessionAidlInterface iOtaSessionAidlInterface = this._aidl;
        if (iOtaSessionAidlInterface == null) {
            return 0;
        }
        try {
            return iOtaSessionAidlInterface.getOtaConsumingTime();
        } catch (RemoteException e) {
            Log.d("OtaSessionProxy", "getOtaConsumingTime error:" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
