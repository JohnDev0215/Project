package com.ts.lib.settings.theme;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.theme.IThemeSettingsCallback;
import com.ts.lib.settings.theme.IThemeSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeSettingsManager implements SettingsAdapterBase {
    private static final String TAG = ThemeSettingsManager.class.getSimpleName();
    private List<OnThemeChangedListener> mThemeChangedListenerList = new ArrayList();
    private final IThemeSettingsCallback mThemeSettingsCallback = new IThemeSettingsCallback.Stub() { // from class: com.ts.lib.settings.theme.ThemeSettingsManager.1
        @Override // com.ts.lib.settings.theme.IThemeSettingsCallback
        public void onINSThemeChanged(int state) {
            ThemeSettingsManager.this.callbackThemeChanged(state);
        }
    };
    private IThemeSettingsService mThemeSettingsService;

    public ThemeSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init theme manager");
        this.mThemeSettingsService = IThemeSettingsService.Stub.asInterface(binder);
        subscribeThemeCallback();
    }

    public void subscribeThemeCallback(OnThemeChangedListener listener) {
        synchronized (this.mThemeChangedListenerList) {
            if (!this.mThemeChangedListenerList.contains(listener) && listener != null) {
                this.mThemeChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeThemeCallback ok --> listener size: " + this.mThemeChangedListenerList.size());
            }
        }
    }

    public void unSubscribeThemeCallback(OnThemeChangedListener listener) {
        synchronized (this.mThemeChangedListenerList) {
            if (listener != null) {
                if (this.mThemeChangedListenerList.contains(listener)) {
                    this.mThemeChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unSubscribeThemeCallback ok --> listener size: " + this.mThemeChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackThemeChanged(int state) {
        for (OnThemeChangedListener listener : this.mThemeChangedListenerList) {
            listener.onThemeChanged(state);
        }
    }

    public void subscribeThemeCallback() {
        if (this.mThemeSettingsService == null) {
            LogUtil.error(TAG, "mThemeSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeThemeCallback");
        try {
            this.mThemeSettingsService.subscribeThemeCallback(this.mThemeSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeThemeCallback error ", ex);
        }
    }

    public void unsubscribeThemeCallback() {
        if (this.mThemeSettingsService == null) {
            LogUtil.error(TAG, "mThemeSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeThemeCallback");
        try {
            this.mThemeSettingsService.unsubscribeThemeCallback(this.mThemeSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeThemeCallback error ", ex);
        }
    }

    public int getINSTheme() {
        if (this.mThemeSettingsService == null) {
            LogUtil.error(TAG, "mThemeSettingsService is null");
            return -1;
        }
        LogUtil.debug(TAG, "getINSTheme");
        try {
            return this.mThemeSettingsService.getINSTheme();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getINSTheme error: ", ex);
            return -1;
        }
    }

    public int setINSTheme(int value) {
        if (this.mThemeSettingsService == null) {
            LogUtil.error(TAG, "mThemeSettingsService is null");
            return -1;
        }
        LogUtil.debug(TAG, "setINSTheme");
        try {
            return this.mThemeSettingsService.setINSTheme(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setINSTheme error: ", ex);
            return -1;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
        this.mThemeSettingsService = null;
        unsubscribeThemeCallback();
    }
}
