package com.ts.appservice.settings.impl.theme;

import android.content.Context;
import android.os.RemoteException;
import com.ts.appservice.settings.impl.qnx.SettingsQnxListener;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.theme.IThemeSettingsCallback;
import com.ts.lib.settings.theme.IThemeSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class IThemeServiceBinder extends IThemeSettingsService.Stub {
    private static final String TAG = IThemeServiceBinder.class.getSimpleName();
    private int mClusterThemeValue;
    private Context mContext;
    private SettingsQnxListener mSettingsQnxListener = new SettingsQnxListener() { // from class: com.ts.appservice.settings.impl.theme.IThemeServiceBinder.1
        @Override // com.ts.appservice.settings.impl.qnx.SettingsQnxListener
        public void onQnxMessageChanged(int key, int value) {
            if (key == 2) {
                IThemeServiceBinder.this.mThemeCallback.onThemeChanged(value);
            }
        }
    };
    private SettingsQnxService mSettingsQnxService;
    private ThemeCallback mThemeCallback;

    public IThemeServiceBinder(Context context) {
        this.mContext = context;
        this.mThemeCallback = new ThemeCallback(this.mContext);
        SettingsQnxService settingsQnxService = SettingsQnxService.getInstance(this.mContext);
        this.mSettingsQnxService = settingsQnxService;
        settingsQnxService.subscribeClusterCallback(this.mSettingsQnxListener);
    }

    public int getINSTheme() {
        this.mClusterThemeValue = this.mSettingsQnxService.getInsTheme();
        LogUtil.debug(TAG, "getINSTheme : " + this.mClusterThemeValue);
        return this.mClusterThemeValue;
    }

    public int setINSTheme(int value) {
        LogUtil.debug(TAG, "setINSTheme value" + value);
        return this.mSettingsQnxService.setINSTheme(value);
    }

    public void subscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mThemeCallback.register(callback);
        }
    }

    public void unsubscribeThemeCallback(IThemeSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mThemeCallback.unregister(callback);
        }
    }

    public void destoryThemeBinder() {
        ThemeCallback themeCallback = this.mThemeCallback;
        if (themeCallback != null) {
            themeCallback.kill();
        }
    }
}
