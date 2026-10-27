package com.ts.lib.settings.location;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.location.ILocationSettingsCallback;
import com.ts.lib.settings.location.ILocationSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LocationSettingsManager implements SettingsAdapterBase {
    private static final String TAG = LocationSettingsManager.class.getSimpleName();
    private List<OnLocationChangedListener> mLocationChangedListenerList = new ArrayList();
    private final ILocationSettingsCallback mLocationSettingsCallback = new ILocationSettingsCallback.Stub() { // from class: com.ts.lib.settings.location.LocationSettingsManager.1
        @Override // com.ts.lib.settings.location.ILocationSettingsCallback
        public void onLocUseChange() {
            LocationSettingsManager.this.callbackLocationUseChanged();
        }
    };
    private ILocationSettingsService mLocationSettingsService;

    public LocationSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init Location manager");
        this.mLocationSettingsService = ILocationSettingsService.Stub.asInterface(binder);
        subscribeLocationCallback();
    }

    public void subscribeLocationCallback(OnLocationChangedListener listener) {
        synchronized (this.mLocationChangedListenerList) {
            if (!this.mLocationChangedListenerList.contains(listener) && listener != null) {
                this.mLocationChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeLocationCallback ok --> listener size: " + this.mLocationChangedListenerList.size());
            }
        }
    }

    public void unSubscribeLocationCallback(OnLocationChangedListener listener) {
        synchronized (this.mLocationChangedListenerList) {
            if (listener != null) {
                if (this.mLocationChangedListenerList.contains(listener)) {
                    this.mLocationChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unSubscribeLocationCallback ok --> listener size: " + this.mLocationChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackLocationUseChanged() {
        for (OnLocationChangedListener listener : this.mLocationChangedListenerList) {
            listener.onLocUseChange();
        }
    }

    private void subscribeLocationCallback() {
        if (this.mLocationSettingsService == null) {
            LogUtil.error(TAG, "mLocationSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeLocationCallback");
        try {
            this.mLocationSettingsService.subscribeLocationCallback(this.mLocationSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeLocationCallback error ", ex);
        }
    }

    private void unsubscribeLocationCallback() {
        if (this.mLocationSettingsService == null) {
            LogUtil.error(TAG, "mLocationSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeLocationCallback");
        try {
            this.mLocationSettingsService.unsubscribeLocationCallback(this.mLocationSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeLocationCallback error ", ex);
        }
    }

    public String[] getLocUsePackageName() {
        ILocationSettingsService iLocationSettingsService = this.mLocationSettingsService;
        if (iLocationSettingsService == null) {
            LogUtil.error(TAG, "mLocationSettingsService is null");
            return null;
        }
        try {
            return iLocationSettingsService.getLocUsePackageName();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getLocUsePackageName error: ", ex);
            return null;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
        this.mLocationSettingsService = null;
        unsubscribeLocationCallback();
    }
}
