package com.ecarx.xui.adaptapi.vehicle.ota;

import android.os.RemoteException;
import android.util.Log;
import com.neusoft.shockwave.tboxclient.IOtaAidlInterface;
import com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface;
import com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
class OtaProxy implements IOta {
    private IOtaAidlInterface _aidl;
    private IOtaSessionAidlInterface _sessionAidl;
    private OtaSessionProxy _sessionProxy;
    private IOtaSessionCallback iOtaSessionCallback;
    private final String cTag = "OtaProxy";
    private IOtaSessionCallbackAidlInterface.Stub _callBack = new IOtaSessionCallbackAidlInterface.Stub() { // from class: com.ecarx.xui.adaptapi.vehicle.ota.OtaProxy.1
        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onBootCompleted() throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onBootCompleted");
                OtaProxy.this.iOtaSessionCallback.onBootCompleted();
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onFailed(int i) throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onFailed");
                OtaProxy.this.iOtaSessionCallback.onFailed(i);
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onInstallStarted(int i) throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onInstallStarted");
                OtaProxy.this.iOtaSessionCallback.onInstallStarted(i);
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onRebootingAfterOta() throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onRebootingAfterOta");
                OtaProxy.this.iOtaSessionCallback.onRebootingAfterOta();
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onSessionCanceled() throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onSessionCanceled");
                OtaProxy.this.iOtaSessionCallback.onSessionCanceled();
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onShouldBeginInstall() throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onShouldBeginInstall");
                OtaProxy.this.iOtaSessionCallback.onShouldBeginInstall();
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onSucceeded() throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onSucceeded");
                OtaProxy.this.iOtaSessionCallback.onSucceeded();
            }
        }

        @Override // com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface
        public void onOtaReminder(int i) throws RemoteException {
            if (OtaProxy.this.iOtaSessionCallback != null) {
                Log.d("OtaProxy", "onOtaReminder");
                if (i == 1) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(1);
                    return;
                }
                if (i == 2) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(2);
                    return;
                }
                if (i == 7) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(7);
                    return;
                }
                if (i == 3) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(3);
                } else if (i == 4) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(4);
                } else if (i == 5) {
                    OtaProxy.this.iOtaSessionCallback.onOtaReminder(5);
                }
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public String getOtaPkgRootPath() {
        return null;
    }

    OtaProxy() {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public int getOtaBaseSoftwareVersionCode() {
        Log.d("OtaProxy", "getOtaBaseSoftwareVersionCode");
        try {
            return this._aidl.getOtaBaseSoftwareVersionCode();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "getOtaBaseSoftwareVersionCode error:" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public String getOtaBaseSoftwareVersionName() {
        Log.d("OtaProxy", "getOtaBaseSoftwareVersionName");
        try {
            return this._aidl.getOtaBaseSoftwareVersionName();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "getOtaBaseSoftwareVersionName error:" + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public String getOtaChangelog() {
        Log.d("OtaProxy", "getOtaChangelog");
        try {
            return this._aidl.getOtaChangelog();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "getOtaChangelog error:" + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public boolean installPackage(IOtaSession iOtaSession, String str) {
        Log.d("OtaProxy", "installPackage");
        try {
            return this._aidl.installPackage(null, str);
        } catch (RemoteException e) {
            Log.d("OtaProxy", "installPackage error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public boolean installPackageByTime(IOtaSession iOtaSession, String str, int i) {
        Log.d("OtaProxy", "installPackageByTime");
        try {
            return this._aidl.installPackageByTime(null, str, i);
        } catch (RemoteException e) {
            Log.d("OtaProxy", "installPackageByTime error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public boolean isNewVersionFound() {
        Log.d("OtaProxy", "isNewVersionFound");
        try {
            return this._aidl.isNewVersionFound();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "isNewVersionFound error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public boolean isOtaPreOdered() {
        Log.d("OtaProxy", "isOtaPreOdered");
        try {
            return this._aidl.isOtaPreOdered();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "isOtaPreOdered error:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public void otaSuspendedByUser() {
        Log.d("OtaProxy", "otaSuspendedByUser");
        try {
            this._aidl.otaSuspendedByUser();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "otaSuspendedByUser error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public IOtaSession requestOta(IOtaSessionCallback iOtaSessionCallback) {
        Log.d("OtaProxy", "requestOta");
        try {
            this.iOtaSessionCallback = iOtaSessionCallback;
            Log.d("OtaProxy", "_sessionProxy = new OtaSessionProxy();");
            OtaSessionProxy otaSessionProxy = new OtaSessionProxy();
            this._sessionProxy = otaSessionProxy;
            return otaSessionProxy;
        } catch (Exception e) {
            Log.d("OtaProxy", "requestOta error:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public void releaseOtaCallback(IOtaSessionCallback iOtaSessionCallback) {
        Log.d("OtaProxy", "releaseOtaCallback");
        this.iOtaSessionCallback = null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ota.IOta
    public int getPackageSize() {
        Log.d("OtaProxy", "getPackageSize");
        try {
            return this._aidl.getPackageSize();
        } catch (RemoteException e) {
            Log.d("OtaProxy", "getPackageSize error:" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public void set_aidl(IOtaAidlInterface iOtaAidlInterface) {
        Log.d("OtaProxy", "set_aidl");
        this._aidl = iOtaAidlInterface;
        try {
            OtaSessionProxy otaSessionProxy = this._sessionProxy;
            if (otaSessionProxy != null) {
                otaSessionProxy.setAidl(iOtaAidlInterface.requestOta(this._callBack));
            } else {
                Log.d("OtaProxy", "set_aidl _sessionProxy = null");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
