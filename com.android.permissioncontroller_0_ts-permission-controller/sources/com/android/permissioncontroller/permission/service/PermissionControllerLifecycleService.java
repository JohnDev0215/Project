package com.android.permissioncontroller.permission.service;

import android.content.Intent;
import android.permission.PermissionControllerService;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PermissionControllerLifecycleService extends PermissionControllerService implements LifecycleOwner {
    private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);

    public void onCreate() {
        this.mDispatcher.onServicePreSuperOnCreate();
        super.onCreate();
    }

    public void onStart(Intent intent, int startId) {
        this.mDispatcher.onServicePreSuperOnStart();
        super.onStart(intent, startId);
    }

    void setLifecycleToStarted() {
        this.mDispatcher.onServicePreSuperOnBind();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public boolean onUnbind(Intent intent) {
        this.mDispatcher.onServicePreSuperOnUnbind();
        return true;
    }

    public void onRebind(Intent intent) {
        this.mDispatcher.onServicePreSuperOnBind();
    }

    public void onDestroy() {
        this.mDispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mDispatcher.getLifecycle();
    }
}
