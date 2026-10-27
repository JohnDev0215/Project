package com.ts.lib.settings.microphone;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.microphone.IMicSettingsCallback;
import com.ts.lib.settings.microphone.IMicSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MicSettingsManager implements SettingsAdapterBase {
    private static final String TAG = MicSettingsManager.class.getSimpleName();
    private List<OnMicChangedListener> mMicChangedListenerList = new ArrayList();
    private final IMicSettingsCallback mMicSettingsCallback = new IMicSettingsCallback.Stub() { // from class: com.ts.lib.settings.microphone.MicSettingsManager.1
        @Override // com.ts.lib.settings.microphone.IMicSettingsCallback
        public void onMicUseChanged(String packageName, boolean status) {
            MicSettingsManager.this.callbackMicUseChanged(packageName, status);
        }
    };
    private IMicSettingsService mMicSettingsService;

    public MicSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init Mic manager");
        this.mMicSettingsService = IMicSettingsService.Stub.asInterface(binder);
        subscribeMicCallback();
    }

    public void subscribeMicCallback(OnMicChangedListener listener) {
        synchronized (this.mMicChangedListenerList) {
            if (!this.mMicChangedListenerList.contains(listener) && listener != null) {
                this.mMicChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeMicCallback ok --> listener size: " + this.mMicChangedListenerList.size());
            }
        }
    }

    public void unSubscribeMicCallback(OnMicChangedListener listener) {
        synchronized (this.mMicChangedListenerList) {
            if (listener != null) {
                if (this.mMicChangedListenerList.contains(listener)) {
                    this.mMicChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unSubscribeMicCallback ok --> listener size: " + this.mMicChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMicUseChanged(String packageName, boolean status) {
        for (OnMicChangedListener listener : this.mMicChangedListenerList) {
            listener.onMicUseChanged(packageName, status);
        }
    }

    private void subscribeMicCallback() {
        if (this.mMicSettingsService == null) {
            LogUtil.error(TAG, "mMicSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeMicCallback");
        try {
            this.mMicSettingsService.subscribeMicCallback(this.mMicSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeMicCallback error ", ex);
        }
    }

    private void unsubscribeMicCallback() {
        if (this.mMicSettingsService == null) {
            LogUtil.error(TAG, "mMicSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeMicCallback");
        try {
            this.mMicSettingsService.unsubscribeMicCallback(this.mMicSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeMicCallback error ", ex);
        }
    }

    public int getChargeStatus() {
        IMicSettingsService iMicSettingsService = this.mMicSettingsService;
        if (iMicSettingsService == null) {
            LogUtil.error(TAG, "mMicSettingsService is null");
            return -1;
        }
        try {
            return iMicSettingsService.getChargeStatus();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getChargeStatus error: ", ex);
            return -1;
        }
    }

    public int getPowerStatus() {
        IMicSettingsService iMicSettingsService = this.mMicSettingsService;
        if (iMicSettingsService == null) {
            LogUtil.error(TAG, "mMicSettingsService is null");
            return -1;
        }
        try {
            return iMicSettingsService.getPowerStatus();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getPowerStatus error: ", ex);
            return -1;
        }
    }

    public String getMicUsePackageName() {
        IMicSettingsService iMicSettingsService = this.mMicSettingsService;
        if (iMicSettingsService == null) {
            LogUtil.error(TAG, "mMicSettingsService is null");
            return null;
        }
        try {
            return iMicSettingsService.getMicUsePackageName();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getMicUsePackageName error: ", ex);
            return null;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
        this.mMicSettingsService = null;
        unsubscribeMicCallback();
    }
}
