package com.vivid.sdk.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MediaManager implements ServiceConnection {
    protected String TAG;
    protected Context context;
    protected String mTargetPackageName = "";
    private RequestCallBack requestCallBack;

    private void setRepeatMode(int i, RequestCallBack requestCallBack) {
    }

    private void setShuffleMode(int i, RequestCallBack requestCallBack) {
    }

    public void addFavorite(RequestCallBack requestCallBack) {
    }

    protected abstract String getServiceAction();

    protected abstract String getServiceClassName();

    public void isLogin(RequestCallBack requestCallBack) {
    }

    public abstract boolean isServiceConnected();

    public void pause(RequestCallBack requestCallBack) {
    }

    public void play(RequestCallBack requestCallBack) {
    }

    public void removeFavorite(RequestCallBack requestCallBack) {
    }

    public void resume(RequestCallBack requestCallBack) {
    }

    public void skipToNext(RequestCallBack requestCallBack) {
    }

    public void skipToPrevious(RequestCallBack requestCallBack) {
    }

    public void stop(RequestCallBack requestCallBack) {
    }

    public MediaManager() {
        this.TAG = "MediaManager";
        this.TAG = getClass().getName();
    }

    public void init(Context context, RequestCallBack requestCallBack) {
        Log.i(this.TAG, "init context is null : " + (context == null));
        this.context = context.getApplicationContext();
        this.requestCallBack = requestCallBack;
        connectService();
    }

    public void destroy() {
        Log.i(this.TAG, "destroy context is null : " + (this.context == null));
        Context context = this.context;
        if (context != null) {
            try {
                context.unbindService(this);
            } catch (Exception e) {
                Log.e(this.TAG, "destroy unbindService  " + e);
            }
        }
    }

    private void connectService() {
        RequestCallBack requestCallBack;
        Intent intent = new Intent();
        intent.setAction(getServiceAction());
        ResolveInfo resolveInfoResolveService = this.context.getPackageManager().resolveService(intent, 131072);
        if (resolveInfoResolveService != null && resolveInfoResolveService.serviceInfo != null) {
            this.mTargetPackageName = resolveInfoResolveService.serviceInfo.packageName;
        }
        intent.setClassName(this.mTargetPackageName, getServiceClassName());
        boolean zBindService = this.context.bindService(intent, this, 1);
        Log.i(this.TAG, "connectService packageName = " + this.mTargetPackageName + "  result = " + zBindService);
        if (zBindService || (requestCallBack = this.requestCallBack) == null) {
            return;
        }
        requestCallBack.onError(ResponseCode.UNKNOW_ERROR);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(this.TAG, "onServiceConnected");
        RequestCallBack requestCallBack = this.requestCallBack;
        if (requestCallBack != null) {
            requestCallBack.onSuccess();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(this.TAG, "onServiceDisconnected");
        RequestCallBack requestCallBack = this.requestCallBack;
        if (requestCallBack != null) {
            requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
        }
    }
}
