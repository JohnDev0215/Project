package com.ts.lib.settings.dms;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.dms.IDmsSettingsCallback;
import com.ts.lib.settings.dms.IDmsSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DmsSettingsManager implements SettingsAdapterBase {
    private IDmsSettingsService mDmsSettingService;
    private final String TAG = DmsSettingsManager.class.getSimpleName();
    private List<OnDmsChangedListener> mDmsChangedListenerList = new ArrayList();
    private final IDmsSettingsCallback mDmsSettingsCallback = new IDmsSettingsCallback.Stub() { // from class: com.ts.lib.settings.dms.DmsSettingsManager.1
        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDmsSwitchChanged(int state) {
            DmsSettingsManager.this.callbackDmsSwitchChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onFatigueSwitchChanged(int state) {
            DmsSettingsManager.this.callbackFatigueSwitchChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onAttentionSwitchChanged(int state) {
            DmsSettingsManager.this.callbackAttentionSwitchChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onFatigueDetectChanged(int state) {
            DmsSettingsManager.this.callbackFatigueDetectChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onAttentionDetectChanged(int state) {
            DmsSettingsManager.this.callbackAttentionDetectChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onBlockStatusChanged(int state) {
            DmsSettingsManager.this.callbackBlockStatusChanged(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDriverNotResponse() {
            DmsSettingsManager.this.callbackDriverNotResponse();
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onRestoreFatigueDetect() {
            DmsSettingsManager.this.callbackRestoreFatigueDetect();
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onNotifyDmsServerState(int state) {
            DmsSettingsManager.this.callbackNotifyDmsServerState(state);
        }

        @Override // com.ts.lib.settings.dms.IDmsSettingsCallback
        public void onDmsModeChanged(int mode) {
            DmsSettingsManager.this.callbackDmsModeChanged(mode);
        }
    };

    public DmsSettingsManager(IBinder binder) {
        LogUtil.debug(this.TAG, "init dms manager");
        if (this.mDmsSettingService == null) {
            this.mDmsSettingService = IDmsSettingsService.Stub.asInterface(binder);
        }
        subscribeDmsCallback();
    }

    public void subscribeDmsCallback(OnDmsChangedListener listener) {
        synchronized (this.mDmsChangedListenerList) {
            if (!this.mDmsChangedListenerList.contains(listener) && listener != null) {
                this.mDmsChangedListenerList.add(listener);
                LogUtil.debug(this.TAG, "subscribeDmsCallback ok --> listener size: " + this.mDmsChangedListenerList.size());
            }
        }
    }

    public void unSubscribeDmsCallback(OnDmsChangedListener listener) {
        synchronized (this.mDmsChangedListenerList) {
            if (listener != null) {
                if (this.mDmsChangedListenerList.contains(listener)) {
                    this.mDmsChangedListenerList.remove(listener);
                    LogUtil.debug(this.TAG, "subscribeDmsCallback ok --> listener size: " + this.mDmsChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDmsSwitchChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onDmsSwitchChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFatigueSwitchChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onFatigueSwitchChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackAttentionSwitchChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onAttentionSwitchChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFatigueDetectChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onFatigueDetectChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackAttentionDetectChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onAttentionDetectChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackBlockStatusChanged(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onBlockStatusChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDriverNotResponse() {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onDriverNotResponse();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackRestoreFatigueDetect() {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onRestoreFatigueDetect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackNotifyDmsServerState(int state) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onNotifyDmsServerState(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDmsModeChanged(int mode) {
        for (OnDmsChangedListener listener : this.mDmsChangedListenerList) {
            listener.onDmsModeChanged(mode);
        }
    }

    private void subscribeDmsCallback() {
        if (this.mDmsSettingService == null) {
            LogUtil.error(this.TAG, "mDmsSettingService is null");
            return;
        }
        LogUtil.debug(this.TAG, "subscribeDmsCallback");
        try {
            this.mDmsSettingService.subscribeDmsCallback(this.mDmsSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "subscribeDmsCallback error ", ex);
        }
    }

    private void unsubscribeDmsCallback() {
        if (this.mDmsSettingService == null) {
            LogUtil.error(this.TAG, "mDmsSettingService is null");
            return;
        }
        LogUtil.debug(this.TAG, "unsubscribeDmsCallback");
        try {
            this.mDmsSettingService.unsubscribeDmsCallback(this.mDmsSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "unsubscribeDmsCallback error ", ex);
        }
    }

    public void queryDmsServiceStatus() {
        try {
            this.mDmsSettingService.queryDmsServiceStatus();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "queryDmsServiceStatus error: ", ex);
        }
    }

    public void setDmsSwtichStatus(int state) {
        try {
            this.mDmsSettingService.setDmsSwtichStatus(state);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "setDmsSwtichStatus error: ", ex);
        }
    }

    public void setPauseFatigueDetect() {
        try {
            this.mDmsSettingService.setPauseFatigueDetect();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "setPauseFatigueDetect error: ", ex);
        }
    }

    public void setFatigueDetect(int state) {
        try {
            this.mDmsSettingService.setFatigueDetect(state);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "setFatigueDetect error: ", ex);
        }
    }

    public void setAttentionDetect(int state) {
        try {
            this.mDmsSettingService.setAttentionDetect(state);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "setAttentionDetect error: ", ex);
        }
    }

    public void getDmsSwitchChanged() {
        try {
            this.mDmsSettingService.getDmsSwitchChanged();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "getDmsSwitchChanged error: ", ex);
        }
    }

    public void queryFatigueDetect() {
        try {
            this.mDmsSettingService.queryFatigueDetect();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "queryFatigueDetect error: ", ex);
        }
    }

    public void queryAttentionDetect() {
        try {
            this.mDmsSettingService.queryAttentionDetect();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "queryAttentionDetect error: ", ex);
        }
    }

    public void setDmsMode(int mode) {
        try {
            this.mDmsSettingService.setDmsMode(mode);
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "setDmsMode error: ", ex);
        }
    }

    public void queryDmsMode() {
        try {
            this.mDmsSettingService.queryDmsMode();
        } catch (RemoteException ex) {
            LogUtil.error(this.TAG, "queryDmsMode error: ", ex);
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        this.mDmsSettingService = null;
        unsubscribeDmsCallback();
    }
}
