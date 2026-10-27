package com.ts.lib.settings.cartips;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.cartips.ICartipsSettingsCallback;
import com.ts.lib.settings.cartips.ICartipsSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CartipsSettingsManager implements SettingsAdapterBase {
    private static final String TAG = CartipsSettingsManager.class.getSimpleName();
    private List<OnCartipsChangedListener> mCartipsChangedListenerList = new ArrayList();
    private final ICartipsSettingsCallback mCartipsSettingsCallback = new ICartipsSettingsCallback.Stub() { // from class: com.ts.lib.settings.cartips.CartipsSettingsManager.1
        @Override // com.ts.lib.settings.cartips.ICartipsSettingsCallback
        public void onCarTipsSoundTypeChanged(int value) {
            CartipsSettingsManager.this.callbackCarTipsSoundTypeChanged(value);
        }
    };
    private ICartipsSettingsService mCartipsSettingsService;

    public CartipsSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init cartips manager");
        this.mCartipsSettingsService = ICartipsSettingsService.Stub.asInterface(binder);
        subscribeCartipsCallback();
    }

    public void subscribeCartipsCallback(OnCartipsChangedListener listener) {
        synchronized (this.mCartipsChangedListenerList) {
            if (!this.mCartipsChangedListenerList.contains(listener) && listener != null) {
                this.mCartipsChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeCartipsCallback ok --> listener size: " + this.mCartipsChangedListenerList.size());
            }
        }
    }

    public void unsubscribeCartipsCallback(OnCartipsChangedListener listener) {
        synchronized (this.mCartipsChangedListenerList) {
            if (listener != null) {
                if (this.mCartipsChangedListenerList.contains(listener)) {
                    this.mCartipsChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unsubscribeClusterCallback ok --> listener size: " + this.mCartipsChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCarTipsSoundTypeChanged(int value) {
        for (OnCartipsChangedListener listener : this.mCartipsChangedListenerList) {
            listener.onCarTipsSoundTypeChanged(value);
        }
    }

    private void subscribeCartipsCallback() {
        if (this.mCartipsSettingsService == null) {
            LogUtil.error(TAG, "mCartipsSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeCartipsCallback");
        try {
            this.mCartipsSettingsService.subscribeCartipsCallback(this.mCartipsSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeCartipsCallback error ", ex);
        }
    }

    private void unsubscribeCartipsCallback() {
        if (this.mCartipsSettingsService == null) {
            LogUtil.error(TAG, "unsubscribeCartipsCallback is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeCartipsCallback");
        try {
            this.mCartipsSettingsService.unsubscribeCartipsCallback(this.mCartipsSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeCartipsCallback error ", ex);
        }
    }

    public int setCarTipsSoundType(int value) {
        LogUtil.debug(TAG, "setCarTipsSoundType : " + value);
        ICartipsSettingsService iCartipsSettingsService = this.mCartipsSettingsService;
        if (iCartipsSettingsService == null) {
            LogUtil.error(TAG, "mCartipsSettingsService is null");
            return -1;
        }
        try {
            return iCartipsSettingsService.setCarTipsSoundType(value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "mCartipsSettingsService error: ", ex);
            return -1;
        }
    }

    public int getCarTipsSoundType() {
        LogUtil.debug(TAG, "getCarTipsSoundType");
        ICartipsSettingsService iCartipsSettingsService = this.mCartipsSettingsService;
        if (iCartipsSettingsService == null) {
            LogUtil.error(TAG, "mCartipsSettingsService is null");
            return -1;
        }
        try {
            return iCartipsSettingsService.getCarTipsSoundType();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "mCartipsSettingsService error: ", ex);
            return -1;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
    }
}
