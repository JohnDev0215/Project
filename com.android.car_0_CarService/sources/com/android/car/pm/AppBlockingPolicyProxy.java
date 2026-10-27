package com.android.car.pm;

import android.car.content.pm.CarAppBlockingPolicy;
import android.car.content.pm.ICarAppBlockingPolicy;
import android.car.content.pm.ICarAppBlockingPolicySetter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.android.car.CarLog;

/* JADX INFO: loaded from: classes3.dex */
public class AppBlockingPolicyProxy implements ServiceConnection {
    private static final int MAX_CRASH_RETRY = 2;
    private static final long TIMEOUT_MS = 5000;
    private boolean mBound;
    private final Context mContext;
    private int mCrashCount;
    private final Handler mHandler;
    private ICarAppBlockingPolicy mPolicyService;
    private final CarPackageManagerService mService;
    private final ServiceInfo mServiceInfo;
    private final Object mLock = new Object();
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: com.android.car.pm.AppBlockingPolicyProxy.1
        @Override // java.lang.Runnable
        public void run() {
            Log.w(CarLog.TAG_PACKAGE, "Timeout for policy setting for service:" + AppBlockingPolicyProxy.this.mServiceInfo);
            AppBlockingPolicyProxy.this.disconnect();
            AppBlockingPolicyProxy.this.mService.onPolicyConnectionFailure(AppBlockingPolicyProxy.this);
        }
    };
    private final ICarAppBlockingPolicySetterImpl mSetter = new ICarAppBlockingPolicySetterImpl();

    public AppBlockingPolicyProxy(CarPackageManagerService service, Context context, ServiceInfo serviceInfo) {
        this.mService = service;
        this.mContext = context;
        this.mServiceInfo = serviceInfo;
        this.mHandler = new Handler(this.mService.getLooper());
    }

    public String getPackageName() {
        return this.mServiceInfo.packageName;
    }

    public void connect() {
        Intent intent = new Intent();
        intent.setComponent(this.mServiceInfo.getComponentName());
        this.mContext.bindServiceAsUser(intent, this, 65, UserHandle.CURRENT_OR_SELF);
        synchronized (this.mLock) {
            this.mBound = true;
        }
        this.mHandler.postDelayed(this.mTimeoutRunnable, TIMEOUT_MS);
    }

    public void disconnect() {
        synchronized (this.mLock) {
            if (this.mBound) {
                this.mBound = false;
                this.mPolicyService = null;
                this.mHandler.removeCallbacks(this.mTimeoutRunnable);
                try {
                    this.mContext.unbindService(this);
                } catch (IllegalArgumentException e) {
                    Log.w(CarLog.TAG_PACKAGE, "unbind", e);
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        ICarAppBlockingPolicy policy;
        boolean failed = false;
        synchronized (this.mLock) {
            policy = ICarAppBlockingPolicy.Stub.asInterface(service);
            this.mPolicyService = policy;
            if (policy == null) {
                failed = true;
            }
        }
        if (!failed) {
            try {
                policy.setAppBlockingPolicySetter(this.mSetter);
                return;
            } catch (RemoteException e) {
                return;
            }
        }
        Log.w(CarLog.TAG_PACKAGE, "Policy service connected with null binder:" + name);
        this.mService.onPolicyConnectionFailure(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        boolean failed = false;
        synchronized (this.mLock) {
            int i = this.mCrashCount + 1;
            this.mCrashCount = i;
            if (i > 2) {
                this.mPolicyService = null;
                failed = true;
            }
        }
        if (failed) {
            Log.w(CarLog.TAG_PACKAGE, "Policy service keep crashing, giving up:" + name);
            this.mService.onPolicyConnectionFailure(this);
        }
    }

    public String toString() {
        return "AppBlockingPolicyProxy [mServiceInfo=" + this.mServiceInfo + ", mCrashCount=" + this.mCrashCount + "]";
    }

    private class ICarAppBlockingPolicySetterImpl extends ICarAppBlockingPolicySetter.Stub {
        private ICarAppBlockingPolicySetterImpl() {
        }

        public void setAppBlockingPolicy(CarAppBlockingPolicy policy) {
            AppBlockingPolicyProxy.this.mHandler.removeCallbacks(AppBlockingPolicyProxy.this.mTimeoutRunnable);
            if (policy == null) {
                Log.w(CarLog.TAG_PACKAGE, "setAppBlockingPolicy null policy from policy service:" + AppBlockingPolicyProxy.this.mServiceInfo);
            }
            AppBlockingPolicyProxy.this.mService.onPolicyConnectionAndSet(AppBlockingPolicyProxy.this, policy);
        }
    }
}
