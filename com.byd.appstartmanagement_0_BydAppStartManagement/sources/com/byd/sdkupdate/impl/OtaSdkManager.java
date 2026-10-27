package com.byd.sdkupdate.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.byd.sdkupdate.aidl.IOtaSdkCallback;
import com.byd.sdkupdate.aidl.IOtaSdkCommand;

/* JADX INFO: loaded from: classes2.dex */
public class OtaSdkManager {
    private static final String ACTION_OTA_UPGRADE_SDK = "com.intent.action.OTA_UPGRADE_SDK";
    private static final String CLASS_NAME = "com.byd.ota.view.OtaUiService";
    private static final String PACKAGE_NAME = "com.byd.ota";
    private static OtaSdkManager otaSdkManager;
    private Context context;
    private OtaSdkManagerCallback mOtaSdkManagerCallback;
    private IOtaSdkCommand otaSdkCommand;
    private ServiceConnectionCallback serviceConnectionCallback;
    private ServiceConnection connection = new ServiceConnection() { // from class: com.byd.sdkupdate.impl.OtaSdkManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OtaSdkManager.this.otaSdkCommand = IOtaSdkCommand.Stub.asInterface(iBinder);
            try {
                OtaSdkManager.this.otaSdkCommand.asBinder().linkToDeath(OtaSdkManager.this.mDeathProxy, 0);
                OtaSdkManager.this.otaSdkCommand.registerCallback(OtaSdkManager.this.otaSdkCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (OtaSdkManager.this.serviceConnectionCallback != null) {
                OtaSdkManager.this.serviceConnectionCallback.onServiceConnected(componentName, iBinder);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (OtaSdkManager.this.otaSdkCommand != null) {
                try {
                    OtaSdkManager.this.otaSdkCommand.unregisterCallback(OtaSdkManager.this.otaSdkCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                OtaSdkManager.this.otaSdkCommand = null;
            }
            if (OtaSdkManager.this.serviceConnectionCallback != null) {
                OtaSdkManager.this.serviceConnectionCallback.onServiceDisconnected(componentName);
            }
        }
    };
    private IBinder.DeathRecipient mDeathProxy = new IBinder.DeathRecipient() { // from class: com.byd.sdkupdate.impl.OtaSdkManager.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (OtaSdkManager.this.otaSdkCommand == null) {
                return;
            }
            OtaSdkManager.this.otaSdkCommand.asBinder().unlinkToDeath(OtaSdkManager.this.mDeathProxy, 0);
            OtaSdkManager.this.otaSdkCommand = null;
            OtaSdkManager.this.bindService();
        }
    };
    private IOtaSdkCallback otaSdkCallback = new IOtaSdkCallback.Stub() { // from class: com.byd.sdkupdate.impl.OtaSdkManager.3
        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void onUpdateFlag(boolean z) throws RemoteException {
            if (OtaSdkManager.this.mOtaSdkManagerCallback != null) {
                OtaSdkManager.this.mOtaSdkManagerCallback.onUpdateFlag(z);
            }
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void onUpdateTime(long j) throws RemoteException {
            if (OtaSdkManager.this.mOtaSdkManagerCallback != null) {
                OtaSdkManager.this.mOtaSdkManagerCallback.onUpdateTime(j);
            }
        }
    };

    public interface ServiceConnectionCallback {
        void onServiceConnected(ComponentName componentName, IBinder iBinder);

        void onServiceDisconnected(ComponentName componentName);
    }

    private OtaSdkManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.setAction(ACTION_OTA_UPGRADE_SDK);
        this.context.bindService(intent, this.connection, 1);
    }

    public static synchronized OtaSdkManager getInstance(Context context, ServiceConnectionCallback serviceConnectionCallback) {
        if (otaSdkManager == null) {
            otaSdkManager = new OtaSdkManager(context, serviceConnectionCallback);
        }
        return otaSdkManager;
    }

    public void destoryManager() throws RuntimeException {
        if (otaSdkManager != null) {
            this.context.unbindService(this.connection);
            otaSdkManager = null;
            this.serviceConnectionCallback = null;
        }
    }

    public String getCurVersion() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                return iOtaSdkCommand.getCurVersion();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLatestVersion() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                return iOtaSdkCommand.getLatestVersion();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getUpdateFlag() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                return iOtaSdkCommand.getUpdateFlag();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getUpdateTime() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                return iOtaSdkCommand.getUpdateTime();
            }
            return 0L;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void registerCallback(OtaSdkManagerCallback otaSdkManagerCallback) {
        this.mOtaSdkManagerCallback = otaSdkManagerCallback;
    }

    public void setCancelDelay() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                iOtaSdkCommand.setCancelDelay();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setOtaUpdate() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                iOtaSdkCommand.setOtaUpdate();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setUserClickCheckState() {
        try {
            IOtaSdkCommand iOtaSdkCommand = this.otaSdkCommand;
            if (iOtaSdkCommand != null) {
                iOtaSdkCommand.setUserClickCheckState();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterCallback(OtaSdkManagerCallback otaSdkManagerCallback) {
        this.mOtaSdkManagerCallback = null;
    }

    private OtaSdkManager(Context context, ServiceConnectionCallback serviceConnectionCallback) {
        this.context = context;
        this.serviceConnectionCallback = serviceConnectionCallback;
        bindService();
    }
}
