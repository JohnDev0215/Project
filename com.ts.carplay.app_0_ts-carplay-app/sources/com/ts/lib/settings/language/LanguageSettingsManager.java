package com.ts.lib.settings.language;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.language.ILanguageSettingsCallback;
import com.ts.lib.settings.language.ILanguageSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LanguageSettingsManager implements SettingsAdapterBase {
    private static final String TAG = LanguageSettingsManager.class.getSimpleName();
    private List<OnLanguageChangedListener> mLanguageChangedListenerList = new ArrayList();
    private final ILanguageSettingsCallback mLanguageSettingsCallback = new ILanguageSettingsCallback.Stub() { // from class: com.ts.lib.settings.language.LanguageSettingsManager.1
        @Override // com.ts.lib.settings.language.ILanguageSettingsCallback
        public void onLanguageChanged(int value) {
            LanguageSettingsManager.this.callbackLanguageChanged(value);
        }
    };
    private ILanguageSettingsService mLanguageSettingsService;

    public LanguageSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init language manager");
        this.mLanguageSettingsService = ILanguageSettingsService.Stub.asInterface(binder);
        subscribeLanguageCallback();
    }

    public void subscribeLanguageCallback(OnLanguageChangedListener listener) {
        synchronized (this.mLanguageChangedListenerList) {
            if (!this.mLanguageChangedListenerList.contains(listener) && listener != null) {
                this.mLanguageChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeLanguageCallback ok --> listener size: " + this.mLanguageChangedListenerList.size());
            }
        }
    }

    public void unSubscribeLanguageCallback(OnLanguageChangedListener listener) {
        synchronized (this.mLanguageChangedListenerList) {
            if (listener != null) {
                if (this.mLanguageChangedListenerList.contains(listener)) {
                    this.mLanguageChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unSubscribeLanguageCallback ok --> listener size: " + this.mLanguageChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackLanguageChanged(int value) {
        for (OnLanguageChangedListener listener : this.mLanguageChangedListenerList) {
            listener.onLanguageChanged(value);
        }
    }

    public void subscribeLanguageCallback() {
        if (this.mLanguageSettingsService == null) {
            LogUtil.error(TAG, "mLanguageSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeLanguageCallback");
        try {
            this.mLanguageSettingsService.subscribeLanguageCallback(this.mLanguageSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeLanguageCallback error ", ex);
        }
    }

    public void unsubscribeLanguageCallback() {
        if (this.mLanguageSettingsService == null) {
            LogUtil.error(TAG, "mLanguageSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribelanguageCallback");
        try {
            this.mLanguageSettingsService.unsubscribeLanguageCallback(this.mLanguageSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeLanguageCallback error ", ex);
        }
    }

    public int setLanguage(int value) {
        LogUtil.debug(TAG, "setLanguage : " + value);
        ILanguageSettingsService iLanguageSettingsService = this.mLanguageSettingsService;
        if (iLanguageSettingsService == null) {
            LogUtil.error(TAG, "mLanguageSettingsService is null");
            return -1;
        }
        try {
            return iLanguageSettingsService.setLanguage(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setLanguage error: ", ex);
            return -1;
        }
    }

    public int getLanguage() {
        LogUtil.debug(TAG, "getLanguage");
        ILanguageSettingsService iLanguageSettingsService = this.mLanguageSettingsService;
        if (iLanguageSettingsService == null) {
            LogUtil.error(TAG, "mLanguageSettingsService is null");
            return -1;
        }
        try {
            return iLanguageSettingsService.getLanguage();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getLanguage error: ", ex);
            return -1;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
        this.mLanguageSettingsService = null;
        unsubscribeLanguageCallback();
    }
}
