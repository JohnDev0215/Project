package com.android.server.telecom.callfiltering;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecom.Log;
import android.telecom.TelecomManager;
import com.android.internal.telecom.ICallScreeningAdapter;
import com.android.internal.telecom.ICallScreeningService;
import com.android.server.telecom.AppLabelProxy;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallScreeningServiceHelper;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.ParcelableCallUtils;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/* JADX INFO: loaded from: classes.dex */
public class CallScreeningServiceFilter extends CallFilter {
    private CharSequence mAppName;
    private final Call mCall;
    private final CallsManager mCallsManager;
    private CallScreeningServiceConnection mConnection;
    private Context mContext;
    private PackageManager mPackageManager;
    private final String mPackageName;
    private final int mPackagetype;
    private final ParcelableCallUtils.Converter mParcelableCallUtilsConverter;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean packageTypeShouldAdd(int i) {
        return i != 0;
    }

    private class CallScreeningAdapter extends ICallScreeningAdapter.Stub {
        private CompletableFuture<CallFilteringResult> mResultFuture;

        public CallScreeningAdapter(CompletableFuture<CallFilteringResult> completableFuture) {
            this.mResultFuture = completableFuture;
        }

        public void allowCall(String str) {
            Long lValueOf = Long.valueOf(Binder.clearCallingIdentity());
            Log.startSession("NCSSF.aC");
            try {
                if (CallScreeningServiceFilter.this.mCall == null || !CallScreeningServiceFilter.this.mCall.getId().equals(str)) {
                    Log.w(this, "allowCall, unknown call id: %s", new Object[]{str});
                }
                Log.addEvent(CallScreeningServiceFilter.this.mCall, "SCREENING_COMPLETED", CallScreeningServiceFilter.this.mPriorStageResult);
                this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
            } finally {
                CallScreeningServiceFilter.this.unbindCallScreeningService();
                Binder.restoreCallingIdentity(lValueOf.longValue());
                Log.endSession();
            }
        }

        public void disallowCall(String str, boolean z, boolean z2, boolean z3, ComponentName componentName) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            Log.startSession("NCSSF.dC");
            try {
                if (CallScreeningServiceFilter.this.mCall != null && CallScreeningServiceFilter.this.mCall.getId().equals(str)) {
                    CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
                    builder.setShouldAllowCall(false);
                    builder.setShouldReject(z);
                    builder.setShouldSilence(false);
                    builder.setShouldAddToCallLog(z2 || CallScreeningServiceFilter.this.packageTypeShouldAdd(CallScreeningServiceFilter.this.mPackagetype));
                    builder.setShouldShowNotification(z3);
                    builder.setCallBlockReason(1);
                    builder.setCallScreeningAppName(CallScreeningServiceFilter.this.mAppName);
                    builder.setCallScreeningComponentName(componentName.flattenToString());
                    builder.setContactExists(CallScreeningServiceFilter.this.mPriorStageResult.contactExists);
                    CallFilteringResult callFilteringResultBuild = builder.build();
                    Log.addEvent(CallScreeningServiceFilter.this.mCall, "SCREENING_COMPLETED", callFilteringResultBuild);
                    this.mResultFuture.complete(callFilteringResultBuild);
                } else {
                    Log.w(this, "disallowCall, unknown call id: %s", new Object[]{str});
                    this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
                }
            } finally {
                CallScreeningServiceFilter.this.unbindCallScreeningService();
                Log.endSession();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public void silenceCall(String str) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            Log.startSession("NCSSF.sC");
            try {
                if (CallScreeningServiceFilter.this.mCall != null && CallScreeningServiceFilter.this.mCall.getId().equals(str)) {
                    CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
                    builder.setShouldAllowCall(true);
                    builder.setShouldReject(false);
                    builder.setShouldSilence(true);
                    builder.setShouldAddToCallLog(true);
                    builder.setShouldShowNotification(true);
                    builder.setContactExists(CallScreeningServiceFilter.this.mPriorStageResult.contactExists);
                    CallFilteringResult callFilteringResultBuild = builder.build();
                    Log.addEvent(CallScreeningServiceFilter.this.mCall, "SCREENING_COMPLETED", callFilteringResultBuild);
                    this.mResultFuture.complete(callFilteringResultBuild);
                } else {
                    Log.w(this, "silenceCall, unknown call id: %s", new Object[]{str});
                    this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
                }
            } finally {
                CallScreeningServiceFilter.this.unbindCallScreeningService();
                Log.endSession();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public void screenCallFurther(String str) {
            if (CallScreeningServiceFilter.this.mPackagetype != 1) {
                throw new SecurityException("Only the default/system dialer may request screen viabackground call audio");
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            Log.startSession("NCSSF.sCF");
            try {
                if (CallScreeningServiceFilter.this.mCall != null && CallScreeningServiceFilter.this.mCall.getId().equals(str)) {
                    CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
                    builder.setShouldAllowCall(true);
                    builder.setShouldReject(false);
                    builder.setShouldSilence(false);
                    builder.setShouldScreenViaAudio(true);
                    builder.setCallScreeningAppName(CallScreeningServiceFilter.this.mAppName);
                    builder.setContactExists(CallScreeningServiceFilter.this.mPriorStageResult.contactExists);
                    CallFilteringResult callFilteringResultBuild = builder.build();
                    Log.addEvent(CallScreeningServiceFilter.this.mCall, "SCREENING_COMPLETED", callFilteringResultBuild);
                    this.mResultFuture.complete(callFilteringResultBuild);
                } else {
                    Log.w(this, "screenCallFurther, unknown call id: %s", new Object[]{str});
                    this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
                }
            } finally {
                CallScreeningServiceFilter.this.unbindCallScreeningService();
                Log.endSession();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private class CallScreeningServiceConnection implements ServiceConnection {
        private CompletableFuture<CallFilteringResult> mResultFuture;

        public CallScreeningServiceConnection(CompletableFuture<CallFilteringResult> completableFuture) {
            this.mResultFuture = completableFuture;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                ICallScreeningService.Stub.asInterface(iBinder).screenCall(CallScreeningServiceFilter.this.new CallScreeningAdapter(this.mResultFuture), CallScreeningServiceFilter.this.mParcelableCallUtilsConverter.toParcelableCallForScreening(CallScreeningServiceFilter.this.mCall, CallScreeningServiceFilter.this.isSystemDialer()));
            } catch (RemoteException e) {
                Log.e(this, e, "Failed to set the call screening adapter", new Object[0]);
                this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
            }
            Log.addEvent(CallScreeningServiceFilter.this.mCall, "SCREENING_BOUND", componentName);
            Log.i(this, "Binding completed.", new Object[0]);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
            Log.i(this, "Service disconnected.", new Object[0]);
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
            Log.i(this, "Binding died.", new Object[0]);
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            this.mResultFuture.complete(CallScreeningServiceFilter.this.mPriorStageResult);
            Log.i(this, "Null binding.", new Object[0]);
            CallScreeningServiceFilter.this.unbindCallScreeningService();
        }
    }

    public CallScreeningServiceFilter(Call call, String str, int i, Context context, CallsManager callsManager, AppLabelProxy appLabelProxy, ParcelableCallUtils.Converter converter) {
        this.mCall = call;
        this.mPackageName = str;
        this.mPackagetype = i;
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mCallsManager = callsManager;
        this.mAppName = appLabelProxy.getAppLabel(this.mPackageName);
        this.mParcelableCallUtilsConverter = converter;
    }

    @Override // com.android.server.telecom.callfiltering.CallFilter
    public CompletionStage<CallFilteringResult> startFilterLookup(CallFilteringResult callFilteringResult) {
        this.mPriorStageResult = callFilteringResult;
        if (this.mPackageName == null) {
            return CompletableFuture.completedFuture(callFilteringResult);
        }
        if (!callFilteringResult.shouldAllowCall) {
            return CompletableFuture.completedFuture(callFilteringResult);
        }
        if (callFilteringResult.contactExists && !hasReadContactsPermission()) {
            return CompletableFuture.completedFuture(callFilteringResult);
        }
        CompletableFuture<CallFilteringResult> completableFuture = new CompletableFuture<>();
        bindCallScreeningService(completableFuture);
        return completableFuture;
    }

    public String toString() {
        return super.toString() + ": " + this.mPackageName;
    }

    private boolean hasReadContactsPermission() {
        int iCheckPermission;
        int i = this.mPackagetype;
        if (i == 0 || i == 1) {
            iCheckPermission = 0;
        } else {
            PackageManager packageManager = this.mPackageManager;
            iCheckPermission = packageManager != null ? packageManager.checkPermission("android.permission.READ_CONTACTS", this.mPackageName) : -1;
        }
        return iCheckPermission == 0;
    }

    private void bindCallScreeningService(CompletableFuture<CallFilteringResult> completableFuture) {
        this.mConnection = new CallScreeningServiceConnection(completableFuture);
        if (CallScreeningServiceHelper.bindCallScreeningService(this.mContext, this.mCallsManager.getCurrentUserHandle(), this.mPackageName, this.mConnection)) {
            return;
        }
        Log.i(this, "Call screening service binding failed.", new Object[0]);
        completableFuture.complete(this.mPriorStageResult);
    }

    public void unbindCallScreeningService() {
        CallScreeningServiceConnection callScreeningServiceConnection = this.mConnection;
        if (callScreeningServiceConnection != null) {
            try {
                this.mContext.unbindService(callScreeningServiceConnection);
            } catch (IllegalArgumentException e) {
                Log.i(this, "Exception when unbind service %s : %s", new Object[]{this.mConnection, e.getMessage()});
            }
        }
        this.mConnection = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSystemDialer() {
        if (this.mPackagetype != 1) {
            return false;
        }
        return this.mPackageName.equals(((TelecomManager) this.mContext.getSystemService(TelecomManager.class)).getSystemDialerPackage());
    }
}
