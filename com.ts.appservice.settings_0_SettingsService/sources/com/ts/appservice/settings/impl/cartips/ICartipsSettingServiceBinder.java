package com.ts.appservice.settings.impl.cartips;

import android.content.Context;
import android.os.RemoteException;
import com.ts.appservice.settings.impl.qnx.SettingsQnxListener;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.lib.settings.cartips.ICartipsSettingsCallback;
import com.ts.lib.settings.cartips.ICartipsSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ICartipsSettingServiceBinder extends ICartipsSettingsService.Stub {
    private static final String TAG = ICartipsSettingServiceBinder.class.getSimpleName();
    private CartipsSettingCallback mCartipsSettingCallback;
    private int mChimeSoundValue;
    private Context mContext;
    private SettingsQnxListener mSettingsQnxListener = new SettingsQnxListener() { // from class: com.ts.appservice.settings.impl.cartips.ICartipsSettingServiceBinder.1
        @Override // com.ts.appservice.settings.impl.qnx.SettingsQnxListener
        public void onQnxMessageChanged(int key, int value) {
            if (key == 5) {
                ICartipsSettingServiceBinder.this.mCartipsSettingCallback.onCarTipsSoundTypeChanged(value);
            }
        }
    };
    private SettingsQnxService mSettingsQnxService;

    public ICartipsSettingServiceBinder(Context context) {
        this.mContext = context;
        this.mCartipsSettingCallback = new CartipsSettingCallback(this.mContext);
        SettingsQnxService settingsQnxService = SettingsQnxService.getInstance(this.mContext);
        this.mSettingsQnxService = settingsQnxService;
        settingsQnxService.subscribeClusterCallback(this.mSettingsQnxListener);
    }

    public int setCarTipsSoundType(int value) {
        LogUtil.debug(TAG, "setCarTipsSoundType value :" + value);
        return this.mSettingsQnxService.setCarTipsSoundType(value);
    }

    public int getCarTipsSoundType() {
        this.mChimeSoundValue = this.mSettingsQnxService.getCarTipsSoundType();
        LogUtil.debug(TAG, "getCarTipsSoundType value : " + this.mChimeSoundValue);
        return this.mChimeSoundValue;
    }

    public void subscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mCartipsSettingCallback.register(callback);
        }
    }

    public void unsubscribeCartipsCallback(ICartipsSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mCartipsSettingCallback.unregister(callback);
        }
    }

    public void destoryCartipsSettingBinder() {
        CartipsSettingCallback cartipsSettingCallback = this.mCartipsSettingCallback;
        if (cartipsSettingCallback != null) {
            cartipsSettingCallback.kill();
        }
    }
}
