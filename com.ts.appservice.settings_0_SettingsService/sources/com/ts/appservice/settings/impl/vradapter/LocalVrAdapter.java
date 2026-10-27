package com.ts.appservice.settings.impl.vradapter;

import android.content.Context;
import android.os.RemoteException;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.vradapter.ILocalVrSettingsCallback;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class LocalVrAdapter {
    private static final String TAG = LocalVrAdapter.class.getSimpleName();
    public static final int VR_ACTIVE = 0;
    private Context mContext;
    private ILocalVrSettingsService mLocalVrSettingsService;
    private int mLocalVrstate;
    private ILocalVrSettingsCallback mSettingsStopCallback = new ILocalVrSettingsCallback.Stub() { // from class: com.ts.appservice.settings.impl.vradapter.LocalVrAdapter.1
        public void onLocalVrAppStateChanged(int state) throws RemoteException {
            LogUtil.info(LocalVrAdapter.TAG, "onLocalVrAppStateChanged state:" + state);
            LocalVrAdapter.this.mLocalVrstate = state;
        }
    };

    public LocalVrAdapter(Context mContext, ILocalVrSettingsService localVrSettingsService) {
        this.mContext = mContext;
        this.mLocalVrSettingsService = localVrSettingsService;
        getVrState();
    }

    public int getVrState() {
        LogUtil.info(TAG, "getVrState ");
        ILocalVrSettingsService iLocalVrSettingsService = this.mLocalVrSettingsService;
        if (iLocalVrSettingsService == null) {
            LogUtil.error(TAG, "mLocalVrSettingsService is null");
            return -1;
        }
        try {
            return iLocalVrSettingsService.getVrState();
        } catch (Exception ex) {
            LogUtil.error(TAG, "getVrState error: ", ex);
            return -1;
        }
    }

    public void doStartVr() {
        LogUtil.info(TAG, "doStartVr ");
        ILocalVrSettingsService iLocalVrSettingsService = this.mLocalVrSettingsService;
        if (iLocalVrSettingsService == null) {
            LogUtil.error(TAG, "mLocalVrSettingsService is null");
            return;
        }
        try {
            iLocalVrSettingsService.doStartVr(this.mSettingsStopCallback);
        } catch (Exception ex) {
            LogUtil.error(TAG, "doStartVr error: ", ex);
        }
    }

    public void doStopVr() {
        LogUtil.info(TAG, "doStopVr ");
        ILocalVrSettingsService iLocalVrSettingsService = this.mLocalVrSettingsService;
        if (iLocalVrSettingsService == null) {
            LogUtil.error(TAG, "mLocalVrSettingsService is null");
            return;
        }
        try {
            iLocalVrSettingsService.doStopVr(this.mSettingsStopCallback);
        } catch (Exception ex) {
            LogUtil.error(TAG, "doStopVr error: ", ex);
        }
    }

    public void destroyService() {
        this.mLocalVrSettingsService = null;
        this.mSettingsStopCallback = null;
    }
}
