package com.byd.auto_emanual.update;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.byd.appstore.aidl.IAppUpdate;

/* JADX INFO: loaded from: classes.dex */
public class AppStoreHelper {
    private static final String ACTION_BIND_SERVICE = "com.byd.appstore.update.AidlAppUpdateSerVice";
    private static final String PKG_NAME = "com.byd.appstore";
    ServiceConnection conn = new ServiceConnection() { // from class: com.byd.auto_emanual.update.AppStoreHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            AppStoreHelper.this.conn = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppStoreHelper.this.remoteService = IAppUpdate.Stub.asInterface(service);
        }
    };
    private Context context;
    private OnAppStoreUpDateCallback onAppStoreUpDateCallback;
    private IAppUpdate remoteService;

    public interface OnAppStoreUpDateCallback {
        void OnAppStoreUpDateInfo(boolean checkInBackground, NewVersionInfo newVersionInfo);

        void OnError(boolean checkInBackground, Exception e);

        void OnRemoteServiceIsNull(boolean checkInBackground);
    }

    public AppStoreHelper(Context context, OnAppStoreUpDateCallback onAppStoreUpDateCallback) {
        this.context = context;
        this.onAppStoreUpDateCallback = onAppStoreUpDateCallback;
    }

    public void init() {
        Intent intent = new Intent(ACTION_BIND_SERVICE);
        intent.setPackage(PKG_NAME);
        intent.setFlags(268435456);
        this.context.bindService(intent, this.conn, 1);
    }

    public void check(boolean checkInBackground) {
        if (this.remoteService != null) {
            _check(checkInBackground);
            return;
        }
        OnAppStoreUpDateCallback onAppStoreUpDateCallback = this.onAppStoreUpDateCallback;
        if (onAppStoreUpDateCallback != null) {
            onAppStoreUpDateCallback.OnRemoteServiceIsNull(checkInBackground);
        }
    }

    private void _check(boolean checkInBackground) {
        try {
            String strQueryAppUpdateInfo = this.remoteService.queryAppUpdateInfo(this.context.getPackageName());
            if (this.onAppStoreUpDateCallback != null) {
                if (!TextUtils.isEmpty(strQueryAppUpdateInfo) && !"".equals(strQueryAppUpdateInfo)) {
                    String[] strArrSplit = strQueryAppUpdateInfo.split(",");
                    if (strArrSplit.length > 0) {
                        this.onAppStoreUpDateCallback.OnAppStoreUpDateInfo(checkInBackground, new NewVersionInfo(strArrSplit[0], strArrSplit[1], Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]), Integer.parseInt(strArrSplit[4]), strArrSplit[5], strArrSplit[6]));
                        return;
                    }
                }
                this.onAppStoreUpDateCallback.OnAppStoreUpDateInfo(checkInBackground, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            OnAppStoreUpDateCallback onAppStoreUpDateCallback = this.onAppStoreUpDateCallback;
            if (onAppStoreUpDateCallback != null) {
                onAppStoreUpDateCallback.OnError(checkInBackground, e);
            }
        }
    }

    public void destroy() {
        this.context.unbindService(this.conn);
        this.context = null;
        this.remoteService = null;
        this.onAppStoreUpDateCallback = null;
    }
}
