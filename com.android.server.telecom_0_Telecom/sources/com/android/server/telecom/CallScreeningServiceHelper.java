package com.android.server.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.android.internal.telecom.ICallScreeningAdapter;
import com.android.internal.telecom.ICallScreeningService;
import com.android.server.telecom.ParcelableCallUtils;
import com.android.server.telecom.TelecomSystem;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public class CallScreeningServiceHelper {
    private static final String TAG = "CallScreeningServiceHelper";
    private final Call mCall;
    private final Context mContext;
    private CompletableFuture mFuture;
    private final Session mLoggingSession = Log.createSubsession();
    private String mPackageName;
    private final ParcelableCallUtils.Converter mParcelableCallUtilsConverter;
    private final UserHandle mUserHandle;

    private class CallScreeningAdapter extends ICallScreeningAdapter.Stub {
        private ServiceConnection mServiceConnection;

        public CallScreeningAdapter(ServiceConnection serviceConnection) {
            this.mServiceConnection = serviceConnection;
        }

        public void allowCall(String str) throws RemoteException {
            unbindCallScreeningService();
        }

        public void silenceCall(String str) throws RemoteException {
            unbindCallScreeningService();
        }

        public void screenCallFurther(String str) throws RemoteException {
            unbindCallScreeningService();
        }

        public void disallowCall(String str, boolean z, boolean z2, boolean z3, ComponentName componentName) throws RemoteException {
            unbindCallScreeningService();
        }

        private void unbindCallScreeningService() {
            CallScreeningServiceHelper.this.mContext.unbindService(this.mServiceConnection);
        }
    }

    public CallScreeningServiceHelper(Context context, TelecomSystem.SyncRoot syncRoot, String str, ParcelableCallUtils.Converter converter, UserHandle userHandle, Call call, AppLabelProxy appLabelProxy) {
        this.mContext = context;
        this.mParcelableCallUtilsConverter = converter;
        this.mCall = call;
        this.mUserHandle = userHandle;
        this.mPackageName = str;
    }

    public CompletableFuture process() {
        Log.d(this, "process", new Object[0]);
        return bindAndGetCallIdentification();
    }

    public CompletableFuture bindAndGetCallIdentification() {
        Log.d(this, "bindAndGetCallIdentification", new Object[0]);
        if (this.mPackageName == null) {
            return CompletableFuture.completedFuture(null);
        }
        this.mFuture = new CompletableFuture();
        if (!bindCallScreeningService(this.mContext, this.mUserHandle, this.mPackageName, new ServiceConnection() { // from class: com.android.server.telecom.CallScreeningServiceHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ICallScreeningService iCallScreeningServiceAsInterface = ICallScreeningService.Stub.asInterface(iBinder);
                Log.continueSession(CallScreeningServiceHelper.this.mLoggingSession, "CSSH.oSC");
                try {
                    try {
                        iCallScreeningServiceAsInterface.screenCall(CallScreeningServiceHelper.this.new CallScreeningAdapter(this), CallScreeningServiceHelper.this.mParcelableCallUtilsConverter.toParcelableCallForScreening(CallScreeningServiceHelper.this.mCall, false));
                    } catch (RemoteException unused) {
                        Log.w(CallScreeningServiceHelper.this, "Cancelling call id due to remote exception", new Object[0]);
                        CallScreeningServiceHelper.this.mFuture.complete(null);
                    }
                } finally {
                    Log.endSession();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.continueSession(CallScreeningServiceHelper.this.mLoggingSession, "CSSH.oSD");
                try {
                    if (!CallScreeningServiceHelper.this.mFuture.isDone()) {
                        Log.w(CallScreeningServiceHelper.this, "Cancelling outgoing call screen due to service disconnect.", new Object[0]);
                    }
                    CallScreeningServiceHelper.this.mFuture.complete(null);
                } finally {
                    Log.endSession();
                }
            }
        })) {
            Log.i(this, "bindAndGetCallIdentification - bind failed", new Object[0]);
            Log.addEvent(this.mCall, "BIND_SCREENING", this.mPackageName);
            this.mFuture.complete(null);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallScreeningServiceHelper$-VhGkFz-q9urpX_vd1qv8vcRioc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$bindAndGetCallIdentification$0$CallScreeningServiceHelper();
            }
        }, Timeouts.getCallScreeningTimeoutMillis(this.mContext.getContentResolver()));
        return this.mFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindAndGetCallIdentification$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindAndGetCallIdentification$0$CallScreeningServiceHelper() {
        Log.continueSession(this.mLoggingSession, "CSSH.timeout");
        try {
            if (!this.mFuture.isDone()) {
                Log.w(TAG, "Cancelling call id process due to timeout", new Object[0]);
            }
            this.mFuture.complete(null);
        } finally {
            Log.endSession();
        }
    }

    public static boolean bindCallScreeningService(Context context, UserHandle userHandle, String str, ServiceConnection serviceConnection) {
        if (TextUtils.isEmpty(str)) {
            Log.i(TAG, "PackageName is empty. Not performing call screening.", new Object[0]);
            return false;
        }
        Intent intent = new Intent("android.telecom.CallScreeningService").setPackage(str);
        List listQueryIntentServicesAsUser = context.getPackageManager().queryIntentServicesAsUser(intent, 0, userHandle.getIdentifier());
        if (listQueryIntentServicesAsUser.isEmpty()) {
            Log.i(TAG, str + " has no call screening service defined.", new Object[0]);
            return false;
        }
        ResolveInfo resolveInfo = (ResolveInfo) listQueryIntentServicesAsUser.get(0);
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        if (serviceInfo == null) {
            Log.w(TAG, str + " call screening service has invalid service info", new Object[0]);
            return false;
        }
        String str2 = serviceInfo.permission;
        if (str2 == null || !str2.equals("android.permission.BIND_SCREENING_SERVICE")) {
            Log.w(TAG, "CallScreeningService must require BIND_SCREENING_SERVICE permission: " + resolveInfo.serviceInfo.packageName, new Object[0]);
            return false;
        }
        ServiceInfo serviceInfo2 = resolveInfo.serviceInfo;
        intent.setComponent(new ComponentName(serviceInfo2.packageName, serviceInfo2.name));
        if (!context.bindServiceAsUser(intent, serviceConnection, 67108865, UserHandle.CURRENT)) {
            return false;
        }
        Log.d(TAG, "bindService, found service, waiting for it to connect", new Object[0]);
        return true;
    }
}
