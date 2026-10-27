package com.ts.lib.settings.cluster;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.cluster.IClusterSettingsCallback;
import com.ts.lib.settings.cluster.IClusterSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ClusterSettingsManager implements SettingsAdapterBase {
    private static final String TAG = ClusterSettingsManager.class.getSimpleName();
    private List<OnClusterChangedListener> mClusterChangedListenerList = new ArrayList();
    private final IClusterSettingsCallback mClusterSettingsCallback = new IClusterSettingsCallback.Stub() { // from class: com.ts.lib.settings.cluster.ClusterSettingsManager.1
        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onINSAdasCenterDisplayChanged(int value) {
            ClusterSettingsManager.this.callbackINSAdasCenterDisplayChanged(value);
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onINSCenterDisplay(int value) {
            ClusterSettingsManager.this.callbackINSCenterDisplayChanged(value);
        }

        @Override // com.ts.lib.settings.cluster.IClusterSettingsCallback
        public void onDayNightModeChanged(int mode) {
            ClusterSettingsManager.this.callbackDayNightModeChanged(mode);
        }
    };
    private IClusterSettingsService mClusterSettingsService;

    public ClusterSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init cluster manager");
        this.mClusterSettingsService = IClusterSettingsService.Stub.asInterface(binder);
        subscribeClusterCallback();
    }

    public void subscribeClusterCallback(OnClusterChangedListener listener) {
        synchronized (this.mClusterChangedListenerList) {
            if (!this.mClusterChangedListenerList.contains(listener) && listener != null) {
                this.mClusterChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeClusterCallback ok --> listener size: " + this.mClusterChangedListenerList.size());
            }
        }
    }

    public void unSubscribeClusterCallback(OnClusterChangedListener listener) {
        synchronized (this.mClusterChangedListenerList) {
            if (listener != null) {
                if (this.mClusterChangedListenerList.contains(listener)) {
                    this.mClusterChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "subscribeClusterCallback ok --> listener size: " + this.mClusterChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackINSAdasCenterDisplayChanged(int value) {
        for (OnClusterChangedListener listener : this.mClusterChangedListenerList) {
            listener.onINSAdasCenterDisplayChanged(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackINSCenterDisplayChanged(int value) {
        for (OnClusterChangedListener listener : this.mClusterChangedListenerList) {
            listener.onINSCenterDisplay(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDayNightModeChanged(int mode) {
        for (OnClusterChangedListener listener : this.mClusterChangedListenerList) {
            listener.onDayNightModeChanged(mode);
        }
    }

    private void subscribeClusterCallback() {
        if (this.mClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeClusterCallback");
        try {
            this.mClusterSettingsService.subscribeClusterCallback(this.mClusterSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeClusterCallback error ", ex);
        }
    }

    private void unsubscribeClusterCallback() {
        if (this.mClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeClusterCallback");
        try {
            this.mClusterSettingsService.unsubscribeClusterCallback(this.mClusterSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeClusterCallback error ", ex);
        }
    }

    public int setINSAdasCenterDisplay(int value) {
        LogUtil.debug(TAG, "setINSAdasCenterDisplay : " + value);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setINSAdasCenterDisplay(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setINSAdasCenterDisplay error: ", ex);
            return -1;
        }
    }

    public int getINSAdasCenterDisplay() {
        LogUtil.debug(TAG, "getINSAdasCenterDisplay");
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.getINSAdasCenterDisplay();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getINSAdasCenterDisplay error: ", ex);
            return -1;
        }
    }

    public int setINSCenterDisplay(int value) {
        LogUtil.debug(TAG, "setINSCenterDisplay : " + value);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setINSCenterDisplay(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setINSCenterDisplay error: ", ex);
            return -1;
        }
    }

    public int getINSCenterDisplay() {
        LogUtil.debug(TAG, "getINSCenterDisplay");
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.getINSCenterDisplay();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getINSCenterDisplay error: ", ex);
            return -1;
        }
    }

    public int setDayNightMode(int mode) {
        LogUtil.debug(TAG, "setDayNightMode : " + mode);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setDayNightMode(mode);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setDayNightMode error: ", ex);
            return -1;
        }
    }

    public int getDayNightMode() {
        LogUtil.debug(TAG, "getDayNightMode");
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.getDayNightMode();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getDayNightMode error: ", ex);
            return -1;
        }
    }

    public int setTimeFormat(int value) {
        LogUtil.debug(TAG, "setTimeFormat : " + value);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setTimeFormat(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setTimeFormat error: ", ex);
            return -1;
        }
    }

    public int setSpeedUpCountState(int state) {
        LogUtil.debug(TAG, "setSpeedUpCountState : " + state);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setSpeedUpCountState(state);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setSpeedUpCountState error: ", ex);
            return -1;
        }
    }

    public int setOverseasMapSupplier(int state) {
        LogUtil.debug(TAG, "setOverseasMapSupplier : " + state);
        IClusterSettingsService iClusterSettingsService = this.mClusterSettingsService;
        if (iClusterSettingsService == null) {
            LogUtil.error(TAG, "mClusterSettingsService is null");
            return -1;
        }
        try {
            return iClusterSettingsService.setOverseasMapSupplier(state);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setOverseasMapSupplier error: ", ex);
            return -1;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        this.mClusterSettingsService = null;
        unsubscribeClusterCallback();
    }
}
